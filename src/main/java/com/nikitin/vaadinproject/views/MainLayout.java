package com.nikitin.vaadinproject.views;

import java.util.Locale;

import com.nikitin.vaadinproject.config.security.SecurityService;
import com.nikitin.vaadinproject.views.login.LoginView;
import com.nikitin.vaadinproject.views.login.SignUpView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.i18n.I18NProvider;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import org.springframework.beans.factory.annotation.Autowired;

public class MainLayout extends AppLayout implements LocaleChangeObserver {

    private static final String LOCALE_SAVED = "main.layout.localeSaved";
    private static final String LINK_SIGN_IN = "main.layout.linkSignIn";
    private static final String LINK_SIGN_UP = "main.layout.linkSignUp";
    private static final String LOGOUT = "main.layout.logout";

    ComboBox<Locale> localeChangeComboBox = new ComboBox<>();
    Button linkSignUp = new Button();
    Button linkSignIn = new Button();
    Button logout = new Button();

    public MainLayout(@Autowired SecurityService securityService, @Autowired I18NProvider i18NProvider) {
        addClassName("main_layout");
        setLocaleChangeComboBox(i18NProvider);
        setLinkSignUp();
        setLinkSignIn();
        setLogout(securityService);
        addToNavbar(setHeaderRight(securityService));
    }

    public void setLocaleChangeComboBox(I18NProvider i18NProvider) {
        localeChangeComboBox.setItems(i18NProvider.getProvidedLocales());
        localeChangeComboBox.setItemLabelGenerator(i -> getTranslation(i.getLanguage()));
        localeChangeComboBox.setValue(UI.getCurrent().getLocale());
        localeChangeComboBox.addValueChangeListener(event -> saveLocalPreference(event.getValue()));
    }

    public void setLinkSignUp() {
        linkSignUp.addClickListener(click -> UI.getCurrent().navigate(SignUpView.class));
    }

    public void setLinkSignIn() {
        linkSignIn.addClickListener(click -> UI.getCurrent().navigate(LoginView.class));
    }

    public void setLogout(SecurityService securityService) {
        logout.addClickListener(click -> securityService.logout());
    }

    public HorizontalLayout setHeaderRight(SecurityService securityService) {
        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("main_layout_header_right");
        if (securityService.getAuthenticatedUser() != null) {
            header.add(localeChangeComboBox, logout);
        } else {
            header.add(linkSignIn, linkSignUp, localeChangeComboBox);
        }
        return header;
    }

    private void saveLocalPreference(Locale locale) {
        getUI().get().setLocale(locale);
        localeChangeComboBox.setItemLabelGenerator(i -> getTranslation(i.getLanguage()));
        Notification.show(getTranslation(LOCALE_SAVED));
    }

    @Override
    public void localeChange(LocaleChangeEvent localeChangeEvent) {
        linkSignIn.setText(getTranslation(LINK_SIGN_IN));
        linkSignUp.setText(getTranslation(LINK_SIGN_UP));
        logout.setText(getTranslation(LOGOUT));
    }
}
