package com.nikitin.vaadinproject.views.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.nikitin.vaadinproject.service.SignUpService;
import com.nikitin.vaadinproject.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Sign-Up")
@Route(value = "/sign_up", layout = MainLayout.class)
@AnonymousAllowed
public class SignUpView extends VerticalLayout implements LocaleChangeObserver {

    private static final String CANCEL= "sign_up.view.cancel";
    private static final String SIGN_UP = "sign_up.view.sign_up";
    private static final String TITLE = "sign_up.view.title";
    private static final String USERNAME = "sign_up.view.username";
    private static final String FIRST_NAME = "sign_up.view.first_name";
    private static final String LAST_NAME = "sign_up.view.last_name";
    private static final String AGE = "sign_up.view.age";
    private static final String PASSWORD = "sign_up.view.password";
    private static final String CONFIRM_PASSWORD = "sign_up.view.confirm_password";
    private static final String EMAIL = "sign_up.view.email";

    private final Button signUp = new Button();
    private final Button cancel = new Button();
    private final H1 title = new H1();
    private TextField usernameField = new TextField();
    private final TextField firstNameField = new TextField();
    private final TextField lastNameField = new TextField();
    private final TextField ageField = new TextField();
    private final PasswordField passwordField = new PasswordField();
    private final PasswordField confirmPasswordField = new PasswordField();
    private final EmailField emailField = new EmailField();

    private final SignUpService signUpService;

    public SignUpView(SignUpService signUpService) {
	this.signUpService = signUpService;
	setSignUp();
	setCancel();
	add(setSignUpForm());
    }

    public void setCancel() {
	cancel.addClickListener(click -> UI.getCurrent().navigate(LoginView.class));
    }

    public void setSignUp() {
	signUp.addClickListener(click -> signUpService.savePerson(getValuesSignUp()));
    }

    public Map<String, String> getValuesSignUp() {
	HashMap<String,String> dataPersonArray = new HashMap<>();
	dataPersonArray.put("username", usernameField.getValue());
	dataPersonArray.put("first_name", firstNameField.getValue());
	dataPersonArray.put("last_name", lastNameField.getValue());
	dataPersonArray.put("age", ageField.getValue());
	dataPersonArray.put("password", passwordField.getValue());
	dataPersonArray.put("confirm_password", confirmPasswordField.getValue());
	dataPersonArray.put("email", emailField.getValue());
	return dataPersonArray;
    }

    public Div setSignUpForm() {
	Div div = new Div();
	div.addClassName("sign_up_div");
	FormLayout formLayout = new FormLayout();
	formLayout.add(usernameField, firstNameField, lastNameField, ageField, passwordField, confirmPasswordField, emailField, signUp, cancel);
	div.add(title, formLayout);

	return div;
    }

    @Override
    public void localeChange(LocaleChangeEvent localeChangeEvent) {
	signUp.setText(getTranslation(SIGN_UP));
	cancel.setText(getTranslation(CANCEL));
	title.setText(getTranslation(TITLE));
	usernameField.setLabel(getTranslation(USERNAME));
	firstNameField.setLabel(getTranslation(FIRST_NAME));
	lastNameField.setLabel(getTranslation(LAST_NAME));
	ageField.setLabel(getTranslation(AGE));
	passwordField.setLabel(getTranslation(PASSWORD));
	confirmPasswordField.setLabel(getTranslation(CONFIRM_PASSWORD));
	emailField.setLabel(getTranslation(EMAIL));
    }
}
