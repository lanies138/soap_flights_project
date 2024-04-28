package org.example.service;

import java.io.File;

import org.example.reservations.Ticket;

public interface PDFCreator {
    
    File create(Ticket ticket);
}