package com.nikitin.vaadinproject.views.login;

import com.nikitin.vaadinproject.views.MainLayout;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Login")
@Route(value = "login", layout = MainLayout.class)
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver, LocaleChangeObserver {

    private static final String FORM_ADDITIONAL_INFORMATION = "login.view.additional_information";
    private static final String FORM_TITLE = "login.view.form_title";
    private static final String FORM_USERNAME = "login.view.username";
    private static final String FORM_PASSWORD = "login.view.password";
    private static final String FORM_SUBMIT = "login.view.form_submit";
    private static final String FORM_FORGOT_PASSWORD = "login.view.forgot_password";
    private static final String ERROR_TITLE = "login.view.error_title";
    private static final String ERROR_MESSAGE = "login.view.error_message";
    private static final String APP_NAME_TEXT = "login.view.app_name_text";

    private final LoginForm loginForm;
    private final H1 appNameText = new H1();
    private final LoginI18n i18n = LoginI18n.createDefault();
    private final LoginI18n.Form i18nForm = i18n.getForm();
    private final LoginI18n.ErrorMessage i18nErrorMessage = i18n.getErrorMessage();

    public LoginView() {
        loginForm = setLoginView();
        addClassName("login");
        add(appNameText, loginForm);
    }

    public LoginForm setLoginView() {
        i18n.setForm(i18nForm);
        i18n.setErrorMessage(i18nErrorMessage);

        LoginForm loginForm = new LoginForm();
        loginForm.setAction("login");

        return loginForm;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (event.getLocation()
                        .getQueryParameters()
                        .getParameters()
                        .containsKey("error")) {
            loginForm.setError(true);
        }
    }

    @Override
    public void localeChange(LocaleChangeEvent localeChangeEvent) {
        appNameText.setText(getTranslation(APP_NAME_TEXT));
        i18n.setAdditionalInformation(getTranslation(FORM_ADDITIONAL_INFORMATION));
        i18nForm.setTitle(getTranslation(FORM_TITLE));
        i18nForm.setUsername(getTranslation(FORM_USERNAME));
        i18nForm.setPassword(getTranslation(FORM_PASSWORD));
        i18nForm.setSubmit(getTranslation(FORM_SUBMIT));
        i18nForm.setForgotPassword(getTranslation(FORM_FORGOT_PASSWORD));
        i18nErrorMessage.setTitle(getTranslation(ERROR_TITLE));
        i18nErrorMessage.setMessage(getTranslation(ERROR_MESSAGE));
        loginForm.setI18n(i18n);
    }
}
