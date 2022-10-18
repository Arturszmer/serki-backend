package com.example.serki.service;

import com.example.serki.DTO.*;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferMailService {


    public OfferMailService(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    private final MailSender mailSender;

    public void prepareOffer(OfferDTO offerDTO) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        System.out.println(offerDTO.getTypeOfTrainingDTOS() + "xxcccss");
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
            System.out.println(training + "xxyyxx");
            String trainers = "";
            String trainingStart = "";
            String trainingEnd = "";
            for (TrainerDTO trainerDTO : training.getTrainers()){
                trainers += trainerDTO.getName() + " \n";
            }
            for (PeriodDTO periodDTO : training.getPeriodDTOS()){
                trainingStart += periodDTO.getStartTraining() + "\n";
                trainingEnd += periodDTO.getEndTraining() + "\n";
            }

            result += """
        Training name: %s
        
        Start: %s
        End: %s
        Trainer name:
        %s
        Price: %s
        
        """.formatted(training.getName(),
                    trainingStart,
                    trainingEnd,
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
                + courseMessage(offerDTO.getTypeOfTrainingDTOS()) +
                """
                                        
                        Best regards
                        Inqoo Team
                        """;
        System.out.println(mes);
        return mes;
    }
}
