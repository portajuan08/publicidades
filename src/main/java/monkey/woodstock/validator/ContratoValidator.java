package monkey.woodstock.validator;


import monkey.woodstock.domain.Contrato;
import monkey.woodstock.services.ContratoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
 
@Component
public class ContratoValidator implements Validator {
 
	private ContratoService contratoService;
	
    @Override
    public boolean supports(Class<?> clazz) {
        return Contrato.class.isAssignableFrom(clazz);
    }
    
    @Autowired
    public void setContratoService(ContratoService contratoService) {
        this.contratoService = contratoService;
    }
 
    @Override
    public void validate(Object target, Errors errors) {

    	Contrato Contrato = (Contrato) target;
    	ValidationUtils.rejectIfEmpty(errors, "vendedor", "contrato.vendedor.empty");
    	ValidationUtils.rejectIfEmpty(errors, "precio", "contrato.precio.empty");
    	if (contratoService.getContratoByCliente(Contrato.getCliente().getId()) != null)
    		errors.rejectValue("cliente", "contrato.cliente.duplicate");
 
    }
 
}
