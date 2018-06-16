package com.tau.account.validator;

import com.tau.account.model.Undead;
import com.tau.account.service.interfaces.UndeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UndeadValidator implements Validator {

    @Autowired
    private UndeadService undeadService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Undead.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors){
        Undead undead = (Undead) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        if (undead.getName().length() < 6 || undead.getName().length() > 32) {
            errors.rejectValue("Name", "Size.undeadForm.name");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ability", "NotEmpty");
        if (undead.getAbility().length() < 6 || undead.getAbility().length() > 32) {
            errors.rejectValue("Ability", "Size.undeadForm.ability");
        }

        ValidationUtils.rejectIfEmpty(errors, "strength", "NotEmpty");
        if (undead.getStrength() < 50 || undead.getStrength() > 400) {
            errors.rejectValue("Strength", "Size.undeadForm.strength");
        }

        ValidationUtils.rejectIfEmpty(errors, "health", "NotEmpty");
        if (undead.getHealth() < 50 || undead.getHealth() > 400) {
            errors.rejectValue("Health", "Size.undeadForm.health");
        }


    }
}
