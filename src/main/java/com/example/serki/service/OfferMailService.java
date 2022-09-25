package com.example.serki.service;

import com.example.serki.DTO.*;
import com.example.serki.models.Trainer;
import com.example.serki.models.TrainingPeriod;
import com.example.serki.models.TypeOfTraining;
import com.example.serki.repository.SubCatRepo;
import com.example.serki.repository.TrainerRepo;
import com.example.serki.repository.TypeOfTrainingsRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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
        simpleMailMessage.setSubject(subject());
        simpleMailMessage.setText(message(offerDTO));
        mailSender.send(simpleMailMessage);
    }

    private String subject(){
        return """
                Your offer from Inqoo
                """;
    }

    private String courseMessage(List<TypeOfTrainingDTO> trainings){
        String result = "";
        for (TypeOfTrainingDTO training : trainings){
            String trainers = "";
            for (TrainerDTO trainerDTO : training.getTrainers()){
                trainers += trainerDTO.getName() + " \n";
            }
            result += """
        Training name: %s
        Start: %s
        End: %s
        Trainer name: %s
        Price: %s
        
        """.formatted(training.getName(),
                    training.getPeriodDTOS().getStartTraining(),
                    training.getPeriodDTOS().getEndTraining(),
                    trainers,
                    training.getPrice());
        }
        return result;
    }

    private String message(OfferDTO offerDTO){
        String mes = """
                Hello, 
                your choosen courses:
                """
                + courseMessage(offerDTO.getTraining()) +
                """
                                        
                        Best regards
                        Inqoo Team
                        """;
        System.out.println(mes);
        return mes;
    }
}
