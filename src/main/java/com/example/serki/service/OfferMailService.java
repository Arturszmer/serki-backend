package com.example.serki.service;

import com.example.serki.DTO.Mapper;
import com.example.serki.DTO.OfferDTO;
import com.example.serki.DTO.PeriodDTO;
import com.example.serki.repository.SubCatRepo;
import com.example.serki.repository.TrainerRepo;
import com.example.serki.repository.TypeOfTrainingsRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OfferMailService {


    public OfferMailService(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    private final MailSender mailSender;

    public void prepareOffer(OfferDTO offerDTO) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("serkiinqoo@gmail.com");
        simpleMailMessage.setTo(offerDTO.getEmail());
        simpleMailMessage.setSubject(subject(offerDTO));
        simpleMailMessage.setText(message(offerDTO));
        mailSender.send(simpleMailMessage);
    }

    private String subject(OfferDTO offerDTO){
        String trainingName = offerDTO.getTypeOfTrainingName();
        return """
                Your offer from Inqoo - %s training
                """.formatted(trainingName);
    }

    private String message(OfferDTO offerDTO){
        String trainingName = offerDTO.getTypeOfTrainingName();
        String start = offerDTO.getPeriodDTO().stream().map(PeriodDTO::getStartTraining).findFirst().get().toString();
        String end = offerDTO.getPeriodDTO().stream().map(PeriodDTO::getEndTraining).findFirst().get().toString();
        String trainerName = offerDTO.getTrainerName();
        String price = offerDTO.getPrice().toString();

        return """
                Hello, 
                you have choosen %s course, the dates:
                Start: %s
                End: %s
                Trainer name: %s
                Price: %s
                
                Best regards
                Inqoo Team
                """.formatted(trainingName, start, end, trainerName, price);
    }
}
