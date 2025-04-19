package com.emailmicroservice.infrastructure.amazonses;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.emailmicroservice.adapters.EmailSenderPdfGateway;
import com.emailmicroservice.core.dto.EmailBaseDto;
import com.emailmicroservice.core.exception.EmailServiceException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import jakarta.mail.Message;
import com.itextpdf.layout.element.Paragraph;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Properties;

@Service
public class AmazonSesEmailSenderWithPdf implements EmailSenderPdfGateway {

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    static final Logger LOGGER = LoggerFactory.getLogger("AmazonSesEmailSenderWithPdf");

    @Autowired
    public AmazonSesEmailSenderWithPdf(final AmazonSimpleEmailService amazonSimpleEmailService) {
        this.amazonSimpleEmailService = amazonSimpleEmailService;
    }

    @Override
    public void senderEmailWithPdf(final
                                   EmailBaseDto emailDto) throws EmailServiceException {
        try {
            MimeMessage mimeMessage = createMimeMessageWithAttachment(emailDto);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            mimeMessage.writeTo(outputStream);
            RawMessage rawMessage = new RawMessage(ByteBuffer.wrap(outputStream.toByteArray()));

            SendRawEmailRequest rawEmailRequest = new SendRawEmailRequest(rawMessage);
            amazonSimpleEmailService.sendRawEmail(rawEmailRequest);

        } catch (Exception e) {
            throw new EmailServiceException("Error while sending email with PDF: " + e.getMessage());
        }
    }

    private MimeMessage createMimeMessageWithAttachment(EmailBaseDto emailDto) throws Exception {

        Properties props = System.getProperties();
        props.put("mail.mime.base64.ignoreerrors", "true");
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(emailDto.getFrom()));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailDto.getTo()));
        message.setSubject(emailDto.getSubject());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText("The PDF report is attached.");
        MimeBodyPart attachmentPart = new MimeBodyPart();

        byte[] pdfBytes = generateRandomPdf();
        DataSource dataSource = new ByteArrayDataSource(pdfBytes, "application/pdf");

        attachmentPart.setDataHandler(new DataHandler(dataSource));
        attachmentPart.setFileName("report.pdf");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        multipart.addBodyPart(attachmentPart);

        message.setContent(multipart);

        return message; 
    }


    public byte[] generateRandomPdf() {
        LOGGER.info("Generating random PDF");
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("Random Report"));
            document.add(new Paragraph("Generated at: " + java.time.LocalDateTime.now()));
            document.add(new Paragraph("Random number: " + Math.random()));

            document.close();
            LOGGER.info("Random PDF generated");
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }


}
