
package monkey.woodstock;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import monkey.woodstock.Util.UtilTime;
import monkey.woodstock.domain.Cheque;
import monkey.woodstock.domain.User;
import monkey.woodstock.services.ChequeService;
import monkey.woodstock.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class MandarMailLoader implements ApplicationListener<ContextRefreshedEvent> {

	private ChequeService chequeService;
	private UserService userService;
	
	@Autowired
    public void setChequeRepository(ChequeService chequeService) {
        this.chequeService = chequeService;
    }
	
	@Autowired
    public void setUserRepository(UserService userSerive) {
        this.userService = userSerive;
    }
	
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	List<String> mails = new ArrayList<String>();
    	List<User> users = (List<User>)userService.listAllUsers();
    	for(User user : users)
    		if (user.getMail() != null)
    			mails.add(user.getMail());
    	
    	List<Cheque> cheques = (List<Cheque>) chequeService.listAllCheques();
    	for (Cheque cheque : cheques){
    		if (!cheque.getYaAviso() && UtilTime.esFechaActual(cheque.getFechaCobro())){
    			if (cheque.getEstado() == null)
    		    	SendMail(cheque,mails);
    			
    			cheque.setYaAviso(true);
    			chequeService.saveCheque(cheque);
    		}
    	}
    }
    
    public void SendMail(Cheque cheque, List<String> mails) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        String Username = "sistemacheques@gmail.com";
        String Pass = "hombre1993";
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Username, Pass);
                    }
                });

        try {
        	StringBuffer oText = new StringBuffer();
        	oText.append("Aviso de cheque vencido. \n");
        	oText.append("Datos del cheque:\n");
        	oText.append("-Numero cheque: " + cheque.getnCheque() + "\n");
        	oText.append("-Emisor: " + cheque.getEmisor() + "\n");
        	oText.append("-Cliente: " + cheque.getCliente() + "\n");
        	oText.append("-Importe: " + cheque.getImporte() + "\n");
        	oText.append("-Banco: " + cheque.getBanco().getDescripcion() + "\n");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("portajuan08@gmail.com"));
            message.setSubject("Aviso de cheque vencido.");
            message.setText(oText.toString());

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }    
}