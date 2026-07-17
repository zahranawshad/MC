package com.municipal.municipalsystem.tax;

import com.municipal.municipalsystem.user.User;
import com.municipal.municipalsystem.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaxServiceTest {

    private TaxRepository taxRepository;
    private UserRepository userRepository;
    private TaxPaymentRepository taxPaymentRepository;

    private TaxService taxService;

    @BeforeEach
    void setUp() {

        taxRepository = mock(TaxRepository.class);
        userRepository = mock(UserRepository.class);
        taxPaymentRepository = mock(TaxPaymentRepository.class);

        taxService = new TaxService(
                taxRepository,
                userRepository,
                taxPaymentRepository
        );
    }

    // UT-01
    @Test
    void shouldUpdatePenaltyForOverdueTax() {

        Tax tax = new Tax();
        tax.setAmount(1000.0);
        tax.setPenalty(0.0);
        tax.setTotalAmount(1000.0);
        tax.setStatus(TaxStatus.PENDING);
        tax.setDueDate(LocalDate.now().minusDays(3));

        when(taxRepository.findAll()).thenReturn(List.of(tax));

        taxService.updatePenaltiesAndStatus();

        assertEquals(TaxStatus.OVERDUE, tax.getStatus());
        assertEquals(100.0, tax.getPenalty());
        assertEquals(1100.0, tax.getTotalAmount());

        verify(taxRepository).save(tax);
    }

    // UT-02
    @Test
    void shouldNotUpdatePenaltyForFutureTax() {

        Tax tax = new Tax();
        tax.setAmount(1000.0);
        tax.setPenalty(0.0);
        tax.setTotalAmount(1000.0);
        tax.setStatus(TaxStatus.PENDING);
        tax.setDueDate(LocalDate.now().plusDays(5));

        when(taxRepository.findAll()).thenReturn(List.of(tax));

        taxService.updatePenaltiesAndStatus();

        assertEquals(TaxStatus.PENDING, tax.getStatus());
        assertEquals(0.0, tax.getPenalty());

        verify(taxRepository, never()).save(tax);
    }

    // UT-03
    @Test
    void shouldCreateTaxSuccessfully() {

        User user = new User();

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(taxRepository.save(any(Tax.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Tax tax = taxService.createTax(
                1L,
                TaxType.PROPERTY_TAX,
                5000.0,
                LocalDate.now().plusDays(30)
        );

        assertNotNull(tax);
        assertEquals(5000.0, tax.getAmount());
        assertEquals(TaxStatus.PENDING, tax.getStatus());

        verify(taxRepository).save(any(Tax.class));
    }

    // UT-04
    @Test
    void shouldPayTaxSuccessfully() {

        Tax tax = new Tax();
        tax.setStatus(TaxStatus.PENDING);

        when(taxRepository.findById(1L))
                .thenReturn(Optional.of(tax));

        when(taxPaymentRepository.save(any(TaxPayment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        TaxPayment payment = taxService.payTax(1L, "CASH");

        assertNotNull(payment);
        assertEquals(TaxStatus.PAID, tax.getStatus());

        verify(taxRepository).save(tax);
        verify(taxPaymentRepository).save(any(TaxPayment.class));
    }

    // UT-05
    @Test
    void shouldReturnCurrentUserTaxes() {

        User user = new User();

        Tax tax = new Tax();

        when(userRepository.findByUsername("citizen"))
                .thenReturn(Optional.of(user));

        when(taxRepository.findByUser(user))
                .thenReturn(List.of(tax));

        List<Tax> taxes = taxService.getCurrentUserTaxes("citizen");

        assertEquals(1, taxes.size());

        verify(userRepository).findByUsername("citizen");
        verify(taxRepository).findByUser(user);
    }

}