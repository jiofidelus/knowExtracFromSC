package org.imogene.epicam.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.imogene.admin.client.AdminRenderer;
import org.imogene.admin.client.event.create.CreateCardEntityEvent;
import org.imogene.admin.client.event.create.CreateFieldGroupEvent;
import org.imogene.admin.client.event.create.CreateNotificationEvent;
import org.imogene.admin.client.event.create.CreateProfileEvent;
import org.imogene.admin.client.event.list.ListCardEntityEvent;
import org.imogene.admin.client.event.list.ListFieldGroupEvent;
import org.imogene.admin.client.event.list.ListNotificationEvent;
import org.imogene.admin.client.event.list.ListProfileEvent;
import org.imogene.admin.client.event.view.ViewCardEntityEvent;
import org.imogene.admin.client.event.view.ViewFieldGroupEvent;
import org.imogene.admin.client.event.view.ViewNotificationEvent;
import org.imogene.admin.client.event.view.ViewProfileEvent;
import org.imogene.admin.client.ui.table.panel.CardEntityListPanel;
import org.imogene.admin.client.ui.table.panel.DynamicFieldTemplateListPanel;
import org.imogene.admin.client.ui.table.panel.FieldGroupListPanel;
import org.imogene.admin.client.ui.table.panel.NotificationListPanel;
import org.imogene.admin.client.ui.table.panel.ProfileListPanel;
import org.imogene.admin.client.ui.workflow.panel.CardEntityFormPanel;
import org.imogene.admin.client.ui.workflow.panel.FieldGroupFormPanel;
import org.imogene.admin.client.ui.workflow.panel.NotificationFormPanel;
import org.imogene.admin.client.ui.workflow.panel.ProfileFormPanel;
import org.imogene.web.client.dynamicfields.ui.renderer.DynFieldsRenderer;
import org.imogene.web.client.dynamicfields.ui.workflow.panel.DynamicFieldTemplateFormPanel;
import org.imogene.web.client.event.CreateDynamicFieldTemplateEvent;
import org.imogene.web.client.event.GoHomeEvent;
import org.imogene.web.client.event.HistoryBackEvent;
import org.imogene.web.client.event.ListDynamicFieldTemplateEvent;
import org.imogene.web.client.event.LogoutEvent;
import org.imogene.web.client.event.ViewDynamicFieldTemplateEvent;
import org.imogene.web.client.i18n.BaseNLS;
import org.imogene.web.client.ui.panel.GlassPanel;
import org.imogene.web.client.ui.panel.MainContentPanel;
import org.imogene.web.client.util.ProfileUtil;
import org.imogene.web.client.util.LocalSession;
import org.imogene.web.client.util.TokenHelper;
import org.imogene.web.client.util.TokenHelper.EntityToken;
import org.imogene.web.shared.proxy.ImogActorProxy;
import org.imogene.web.shared.proxy.SessionInfoProxy;
import org.imogene.web.shared.request.SessionRequest;
import org.imogene.epicam.client.event.create.*;
import org.imogene.epicam.client.event.list.*;
import org.imogene.epicam.client.event.view.*;
import org.imogene.epicam.client.ui.workflow.panel.*;
import org.imogene.epicam.client.ui.table.panel.*;
import org.imogene.epicam.client.ui.panel.TopBannerPanel;
import org.imogene.epicam.client.ui.panel.HomePanel;
import org.imogene.epicam.shared.EpicamRequestFactory;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import org.imogene.epicam.client.ui.maps.EpicamMap;

/**
 * Entry point of the application on the client side
 * @author Medes-IMPS
 */
public class EpicamEntryPoint implements EntryPoint, ValueChangeHandler<String> {

	interface Binder extends UiBinder<Widget, EpicamEntryPoint> {
	}

	private static final Logger log = Logger.getLogger(EpicamEntryPoint.class
			.getName());

	private static List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();

	private static String APPLICATION_VERSION = "0.0.1";

	private static final int LOGOUT_TIMEOUT = 333;

	public static GlassPanel GP = new GlassPanel();

	/* session cookie with a validation period of 2 days */
	private static String COOKIE_NAME = "imogene-epicam-admin";
	private final long COOKIE_DURATION = 1000 * 60 * 60 * 24 * 2;

	protected final EpicamRequestFactory requestFactory = GWT
			.create(EpicamRequestFactory.class);
	protected EventBus eventBus = new SimpleEventBus();

	/* timer to check session validity */
	private Timer sessionTimer;

	private int historyCount = 0;

	@UiField
	VerticalPanel mainPanel;
	@UiField
	MainContentPanel content;
	@UiField(provided = true)
	TopBannerPanel topPanel;

	/**
	 * This is the entry point method
	 */
	public void onModuleLoad() {

		GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			public void onUncaughtException(Throwable e) {
				log.log(Level.SEVERE, e.getMessage(), e);
			}
		});

		requestFactory.initialize(eventBus);

		// get session info and set info in local session
		SessionRequest request = requestFactory.sessionInfoRequest();
		Request<SessionInfoProxy> sessionInfoRequest = request.getSessionInfo();
		sessionInfoRequest.with("actor.profiles");
		sessionInfoRequest.with("actor.profiles.entityProfiles");
		sessionInfoRequest.with("actor.profiles.entityProfiles.entity");
		sessionInfoRequest.with("actor.profiles.fieldGroupProfiles");
		sessionInfoRequest.with("actor.profiles.fieldGroupProfiles.fieldGroup");
		sessionInfoRequest.to(new Receiver<SessionInfoProxy>() {

			@Override
			public void onFailure(ServerFailure error) {
				Window.alert(BaseNLS.constants().connexion_error());
			}

			@Override
			public void onSuccess(SessionInfoProxy sessionInfo) {
				if (sessionInfo != null && sessionInfo.getActor() != null) {
					ImogActorProxy actor = sessionInfo.getActor();
					LocalSession.get().setCurrentUser(actor);
					LocalSession.get().setVersionNumber(APPLICATION_VERSION);
					displayApplication(actor);
					initializeCookie(sessionInfo.getSessionId());
				} else
					logout();
			}
		}).fire();
	}

	/**
	 * Initialize the cookie
	 */
	private void initializeCookie(String sessionId) {
		String cookieValue = Cookies.getCookie(COOKIE_NAME);
		if (cookieValue == null || !sessionId.equals(cookieValue)) {
			Cookies.setCookie(COOKIE_NAME, sessionId,
					new Date(System.currentTimeMillis() + COOKIE_DURATION));
		}
		launchSessionTimer();
	}

	/**
	 * Create the user interface
	 */
	protected void displayApplication(ImogActorProxy actor) {

		registrations.add(History.addValueChangeHandler(this));
		//History.fireCurrentHistoryState();

		RootPanel.get("loadingWrapper").setVisible(false);

		// construct layout
		topPanel = new TopBannerPanel(eventBus, actor);

		// get parent panel
		RootPanel root = RootPanel.get("root");
		root.add(GWT.<Binder> create(Binder.class).createAndBindUi(this));

		// add first level panel		
		setContentPanel(getHomePanel());
		topPanel.hideTitle();

		// configure Admin renderer
		AdminRenderer.get().setFormTypeUtil(EpicamFormTypes.get());

		// add handlers
		setHandlers();

		// throw the current history token
		History.fireCurrentHistoryState();
	}

	/**
	 * Gets the module Home panel
	 * @return
	 */
	protected Composite getHomePanel() {
		return new HomePanel(eventBus);
	}

	/**
	 * Sets the module content panel
	 * @param c the panel to be displayed in the module content panel
	 */
	public void setContentPanel(Composite c) {
		content.setContent(c);
	}

	/**
	 * Logouts the user from the application
	 */
	private void logout() {

		/* stop timer */
		if (sessionTimer != null) {
			sessionTimer.cancel();
			sessionTimer = null;
		}

		Cookies.removeCookie(COOKIE_NAME);

		/* clean local data */
		LocalSession.get().setCurrentUser(null);

		// WARNING -> Workaround to the re-connection problem
		Timer logoutTimer = new Timer() {
			public void run() {
				/* disconnect from the server */
				SessionRequest request = requestFactory.sessionInfoRequest();
				Request<Void> disconnectRequest = request.disconnect();
				disconnectRequest.to(new Receiver<Void>() {

					@Override
					public void onFailure(ServerFailure error) {
						clearModule();
					}

					@Override
					public void onSuccess(Void result) {
						clearModule();
					}
				}).fire();
			}
		};
		logoutTimer.schedule(LOGOUT_TIMEOUT);
	}

	/**
	 * Launch a timer that checks every 5s that the current user 
	 * session is valid. If it isn't (because the session expired),
	 * then it displays the login form.
	 */
	private void launchSessionTimer() {

		/* set timer to check session validity and disconnect if not valid */
		if (sessionTimer != null) {
			//Window.alert("Session timer is not null");
		} else {
			sessionTimer = new Timer() {
				public void run() {

					/* check session */
					SessionRequest request = requestFactory
							.sessionInfoRequest();
					Request<Boolean> validateSessionRequest = request
							.validateSession(Cookies.getCookie(COOKIE_NAME));
					validateSessionRequest.to(new Receiver<Boolean>() {

						@Override
						public void onFailure(ServerFailure error) {
							//logout();
						}

						@Override
						public void onSuccess(Boolean result) {
							if (result == null || !result) {
								logout();
							}
						}
					}).fire();
				}
			};
		}
		sessionTimer.cancel();
		sessionTimer.scheduleRepeating(5000);
	}

	/**
	 * Clears the module from its panels
	 */
	private void clearModule() {
		RootPanel.get().clear();
		topPanel = null;
		content = null;
		mainPanel = null;
		removeHandlers();
		String href = GWT.getHostPageBaseURL() + "jsp/Login.jsp";
		Window.Location.assign(href);
	}

	/**
	 * 
	 * @param panel
	 */
	private void displayWrapperPanel(Widget panel) {
		topPanel.showTitle();
		topPanel.showLocaleList(false);
		content.setContent(panel);
	}

	/**
	 * Remove the application level handlers
	 */
	protected static void removeHandlers() {
		for (HandlerRegistration r : registrations)
			r.removeHandler();
		registrations.clear();
	}

	/**
	 *
	 */
	protected <T extends EventHandler> void registerHandler(Type<T> type,
			T eventHandler) {
		registrations.add(eventBus.addHandler(type, eventHandler));
	}

	/**
	 * Set the application level handlers
	 */
	private void setHandlers() {

		/* Dispaley map	 */
		registrations.add(eventBus.addHandler(ViewEpicamMapEvent.TYPE,
				new ViewEpicamMapEvent.Handler() {
					public void viewMap() {
						EpicamMap map = new EpicamMap(requestFactory);
						map.setAutoHideEnabled(true);
						map.setPopupPosition(80, 20);
						map.show();
					}
				}));

		/* Handler to go to the application home page */
		registrations.add(eventBus.addHandler(GoHomeEvent.TYPE,
				new GoHomeEvent.Handler() {
					public void goHome() {
						content.setContent(getHomePanel());
						topPanel.hideTitle();
						topPanel.showLocaleList(true);
					}
				}));

		/* Handler to logout the application */
		registrations.add(eventBus.addHandler(LogoutEvent.TYPE,
				new LogoutEvent.Handler() {
					public void onLogout() {
						logout();
					}
				}));

		registrations.add(eventBus.addHandler(HistoryBackEvent.TYPE,
				new HistoryBackEvent.Handler() {

					@Override
					public void onHistoryBackRequest() {
						if (historyCount > 1) {
							History.back();
							historyCount--;
						} else {
							History.newItem("");
						}
					}
				}));

		/** 
		 * Handlers for entity Personnel 
		 * */

		/* Create Personnel Handler */
		registrations.add(eventBus.addHandler(CreatePersonnelEvent.TYPE,
				new CreatePersonnelEvent.Handler() {
					public void createNewPersonnel(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreatePersonnel()
								&& AccessManager.canEditPersonnel()) {
							PersonnelFormPanel personnelForm = new PersonnelFormPanel(
									requestFactory, null);
							personnelForm.setCloseEvent(closeEvent);
							displayWrapperPanel(personnelForm);
						}
					}
				}));

		/* View Personnel Handler */
		registrations.add(eventBus.addHandler(ViewPersonnelEvent.TYPE,
				new ViewPersonnelEvent.Handler() {
					public void viewPersonnel(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadPersonnel()) {
							PersonnelFormPanel personnelForm = new PersonnelFormPanel(
									requestFactory, entityId);
							personnelForm.setCloseEvent(closeEvent);
							displayWrapperPanel(personnelForm);
						}
					}
				}));

		/* List Personnel Handler	 */
		registrations.add(eventBus.addHandler(ListPersonnelEvent.TYPE,
				new ListPersonnelEvent.Handler() {
					public void listPersonnel() {
						if (AccessManager.canDirectAccessPersonnel()
								&& AccessManager.canReadPersonnel())
							displayWrapperPanel(new PersonnelListPanel(
									requestFactory));
					}
					public void listPersonnel(String searchText) {
						if (AccessManager.canDirectAccessPersonnel()
								&& AccessManager.canReadPersonnel())
							displayWrapperPanel(new PersonnelListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity Utilisateur 
		 * */

		/* Create Utilisateur Handler */
		registrations.add(eventBus.addHandler(CreateUtilisateurEvent.TYPE,
				new CreateUtilisateurEvent.Handler() {
					public void createNewUtilisateur(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateUtilisateur()
								&& AccessManager.canEditUtilisateur()) {
							UtilisateurFormPanel utilisateurForm = new UtilisateurFormPanel(
									requestFactory, null);
							utilisateurForm.setCloseEvent(closeEvent);
							displayWrapperPanel(utilisateurForm);
						}
					}
				}));

		/* View Utilisateur Handler */
		registrations.add(eventBus.addHandler(ViewUtilisateurEvent.TYPE,
				new ViewUtilisateurEvent.Handler() {
					public void viewUtilisateur(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadUtilisateur()) {
							UtilisateurFormPanel utilisateurForm = new UtilisateurFormPanel(
									requestFactory, entityId);
							utilisateurForm.setCloseEvent(closeEvent);
							displayWrapperPanel(utilisateurForm);
						}
					}
				}));

		/* List Utilisateur Handler	 */
		registrations.add(eventBus.addHandler(ListUtilisateurEvent.TYPE,
				new ListUtilisateurEvent.Handler() {
					public void listUtilisateur() {
						if (AccessManager.canDirectAccessUtilisateur()
								&& AccessManager.canReadUtilisateur())
							displayWrapperPanel(new UtilisateurListPanel(
									requestFactory));
					}
					public void listUtilisateur(String searchText) {
						if (AccessManager.canDirectAccessUtilisateur()
								&& AccessManager.canReadUtilisateur())
							displayWrapperPanel(new UtilisateurListPanel(
									requestFactory, searchText));
					}
				}));

		/**
		 * Handlers for entity Notification
		 */

		/* Create Notification Handler */
		registrations.add(eventBus.addHandler(CreateNotificationEvent.TYPE,
				new CreateNotificationEvent.Handler() {
					public void createNewNotification(GwtEvent<?> closeEvent) {
						if (ProfileUtil.isAdmin()) {
							NotificationFormPanel notificationFormPanel = new NotificationFormPanel(
									requestFactory, null,
									EpicamFormTypes.get(), EpicamRenderer.get());
							notificationFormPanel.setCloseEvent(closeEvent);
							displayWrapperPanel(notificationFormPanel);
						}
					}
				}));

		/* View Notification Handler */
		registrations.add(eventBus.addHandler(ViewNotificationEvent.TYPE,
				new ViewNotificationEvent.Handler() {
					public void viewNotification(String entityId,
							GwtEvent<?> closeEvent) {
						if (ProfileUtil.isAdmin()) {
							NotificationFormPanel notificationFormPanel = new NotificationFormPanel(
									requestFactory, entityId, EpicamFormTypes
											.get(), EpicamRenderer.get());
							notificationFormPanel.setCloseEvent(closeEvent);
							displayWrapperPanel(notificationFormPanel);
						}
					}
				}));

		/* List Notification Handler */
		registrations.add(eventBus.addHandler(ListNotificationEvent.TYPE,
				new ListNotificationEvent.Handler() {
					public void listNotification() {
						if (ProfileUtil.isAdmin())
							displayWrapperPanel(new NotificationListPanel(
									requestFactory, EpicamFormTypes.get()));
					}

					public void listNotification(String searchText) {
						if (ProfileUtil.isAdmin())
							displayWrapperPanel(new NotificationListPanel(
									requestFactory, searchText, EpicamFormTypes
											.get()));
					}
				}));

		/**
		 * Handlers for entity DynamicFieldTemplate
		 */

		/* Create DynamicFieldTemplate Handler */
		registrations.add(eventBus.addHandler(
				CreateDynamicFieldTemplateEvent.TYPE,
				new CreateDynamicFieldTemplateEvent.Handler() {
					public void createNewDynamicFieldTemplate(
							GwtEvent<?> closeEvent) {
						if (ProfileUtil.isAdmin()) {
							DynamicFieldTemplateFormPanel dynamicField_TemplateFormPanel = new DynamicFieldTemplateFormPanel(
									requestFactory, null, EpicamFormTypes.get());
							dynamicField_TemplateFormPanel
									.setCloseEvent(closeEvent);
							displayWrapperPanel(dynamicField_TemplateFormPanel);
						}
					}
				}));

		/* View DynamicFieldTemplate Handler */
		registrations.add(eventBus.addHandler(
				ViewDynamicFieldTemplateEvent.TYPE,
				new ViewDynamicFieldTemplateEvent.Handler() {
					public void viewDynamicFieldTemplate(String entityId,
							GwtEvent<?> closeEvent) {
						if (ProfileUtil.isAdmin()) {
							DynamicFieldTemplateFormPanel dynamicField_TemplateFormPanel = new DynamicFieldTemplateFormPanel(
									requestFactory, entityId, EpicamFormTypes
											.get());
							dynamicField_TemplateFormPanel
									.setCloseEvent(closeEvent);
							displayWrapperPanel(dynamicField_TemplateFormPanel);
						}
					}
				}));

		/* List DynamicFieldTemplate Handler */
		registrations.add(eventBus.addHandler(
				ListDynamicFieldTemplateEvent.TYPE,
				new ListDynamicFieldTemplateEvent.Handler() {
					public void listDynamicFieldTemplate() {
						if (ProfileUtil.isAdmin())
							displayWrapperPanel(new DynamicFieldTemplateListPanel(
									requestFactory, EpicamFormTypes.get()));
					}

					public void listDynamicFieldTemplate(String searchText) {
						if (ProfileUtil.isAdmin())
							displayWrapperPanel(new DynamicFieldTemplateListPanel(
									requestFactory, searchText, EpicamFormTypes
											.get()));
					}
				}));

		/* Create Profile Handler */
		registrations.add(eventBus.addHandler(CreateProfileEvent.TYPE,
				new CreateProfileEvent.Handler() {
					public void createNewProfile(GwtEvent<?> closeEvent) {
						if (ProfileUtil.isAdmin()) {
							ProfileFormPanel profileForm = new ProfileFormPanel(
									requestFactory, null,
									EpicamAdminUtilRenderer.get());
							profileForm.setCloseEvent(closeEvent);
							displayWrapperPanel(profileForm);
						}
					}
				}));

		/* View Profile Handler */
		registrations.add(eventBus.addHandler(ViewProfileEvent.TYPE,
				new ViewProfileEvent.Handler() {
					public void viewProfile(String entityId,
							GwtEvent<?> closeEvent) {
						if (ProfileUtil.isAdmin()) {
							ProfileFormPanel profileForm = new ProfileFormPanel(
									requestFactory, entityId,
									EpicamAdminUtilRenderer.get());
							profileForm.setCloseEvent(closeEvent);
							displayWrapperPanel(profileForm);
						}
					}
				}));

		/* List Profile Handler */
		registrations.add(eventBus.addHandler(ListProfileEvent.TYPE,
				new ListProfileEvent.Handler() {
					public void listProfile() {
						if (ProfileUtil.isAdmin())
							displayWrapperPanel(new ProfileListPanel(
									requestFactory));
					}

					public void listProfile(String searchText) {
						if (ProfileUtil.isAdmin())
							displayWrapperPanel(new ProfileListPanel(
									requestFactory, searchText));
					}
				}));

		/**
		 * Handlers for entity CardEntity
		 * */

		/* View CardEntity Handler */
		registrations.add(eventBus.addHandler(ViewCardEntityEvent.TYPE,
				new ViewCardEntityEvent.Handler() {
					public void viewCardEntity(String entityId,
							GwtEvent<?> closeEvent) {
						if (ProfileUtil.isAdmin()) {
							CardEntityFormPanel cardEntityForm = new CardEntityFormPanel(
									requestFactory, entityId,
									EpicamAdminUtilRenderer.get());
							cardEntityForm.setCloseEvent(closeEvent);
							displayWrapperPanel(cardEntityForm);
						}
					}
				}));

		/* List CardEntity Handler */
		registrations.add(eventBus.addHandler(ListCardEntityEvent.TYPE,
				new ListCardEntityEvent.Handler() {
					public void listCardEntity() {
						if (ProfileUtil.isAdmin())
							displayWrapperPanel(new CardEntityListPanel(
									requestFactory, EpicamAdminUtilRenderer
											.get()));
					}

					public void listCardEntity(String searchText) {
						if (ProfileUtil.isAdmin())
							displayWrapperPanel(new CardEntityListPanel(
									requestFactory, searchText,
									EpicamAdminUtilRenderer.get()));
					}
				}));
		/**
		 * Handlers for entity FieldGroup
		 * */

		/* View FieldGroup Handler */
		registrations.add(eventBus.addHandler(ViewFieldGroupEvent.TYPE,
				new ViewFieldGroupEvent.Handler() {
					public void viewFieldGroup(String entityId,
							GwtEvent<?> closeEvent) {
						if (ProfileUtil.isAdmin()) {
							FieldGroupFormPanel fieldgroupForm = new FieldGroupFormPanel(
									requestFactory, entityId,
									EpicamAdminUtilRenderer.get());
							fieldgroupForm.setCloseEvent(closeEvent);
							displayWrapperPanel(fieldgroupForm);
						}
					}
				}));

		/* List FieldGroup Handler */
		registrations.add(eventBus.addHandler(ListFieldGroupEvent.TYPE,
				new ListFieldGroupEvent.Handler() {
					public void listFieldGroup() {
						if (ProfileUtil.isAdmin())
							displayWrapperPanel(new FieldGroupListPanel(
									requestFactory, EpicamAdminUtilRenderer
											.get()));
					}

					public void listFieldGroup(String searchText) {
						if (ProfileUtil.isAdmin())
							displayWrapperPanel(new FieldGroupListPanel(
									requestFactory, searchText,
									EpicamAdminUtilRenderer.get()));
					}
				}));
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {

		historyCount++;
		String token = event.getValue();
		EntityToken entityToken = TokenHelper.getToken(token);

		if (entityToken != null) {

			if (entityToken.getType().equals(TokenHelper.TK_CLASSIC)) {
				eventBus.fireEvent(new GoHomeEvent());
			}

			if (entityToken.getType().equals("rapport")) {
				if (AccessManager.canCreateRapport()) {
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						eventBus.fireEvent(new CreateRapportEvent());
					}
				}
			}

			if (entityToken.getType().equals("envoiSms")) {
				if (AccessManager.canCreateRapport()) {
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						eventBus.fireEvent(new CreateEnvoiSMSEvent());
					}
				}
			}

			/** actions for Personnel */
			if (entityToken.getType().equals("personnel")) {
				if (AccessManager.canReadPersonnel()) {
					/* view for Personnel */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewPersonnelEvent(entityToken
								.getId()));

					/* list for Personnel */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessPersonnel()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListPersonnelEvent());
							else
								eventBus.fireEvent(new ListPersonnelEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditPersonnel()) {
					/* new Personnel */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreatePersonnel())
							eventBus.fireEvent(new CreatePersonnelEvent());
					}
				}
			}
			/** actions for Utilisateur */
			if (entityToken.getType().equals("utilisateur")) {
				if (AccessManager.canReadUtilisateur()) {
					/* view for Utilisateur */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewUtilisateurEvent(entityToken
								.getId()));

					/* list for Utilisateur */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessUtilisateur()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListUtilisateurEvent());
							else
								eventBus.fireEvent(new ListUtilisateurEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditUtilisateur()) {
					/* new Utilisateur */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateUtilisateur())
							eventBus.fireEvent(new CreateUtilisateurEvent());
					}
				}
			}
			/** actions for notifications */
			if (entityToken.getType().equals("notification")) {
				if (ProfileUtil.isAdmin()) {
					/* view for Notification */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewNotificationEvent(
								entityToken.getId()));

					/* list for Notification */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						eventBus.fireEvent(new ListNotificationEvent());
					}
					/* new Notification */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						eventBus.fireEvent(new CreateNotificationEvent());
					}
				}
			}
			/** actions for DynamicField Template */
			if (entityToken.getType().equals("dynamicfieldtemplate")) {
				if (ProfileUtil.isAdmin()) {
					/* view for DynamicField Template */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewDynamicFieldTemplateEvent(
								entityToken.getId()));

					/* list for DynamicField Template */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						eventBus.fireEvent(new ListDynamicFieldTemplateEvent());
					}
					/* new DynamicField Template */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						eventBus.fireEvent(new CreateDynamicFieldTemplateEvent());
					}
				}
			}
			/** actions for FieldGroup */
			if (entityToken.getType().equals("fieldgroup")) {
				if (ProfileUtil.isAdmin()) {
					/* view for FieldGroup */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewFieldGroupEvent(entityToken
								.getId()));

					/* list for FieldGroup */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						eventBus.fireEvent(new ListFieldGroupEvent());
					}
					/* new FieldGroup */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						eventBus.fireEvent(new CreateFieldGroupEvent());
					}
				}
			}
			/** actions fro Profile */
			if (entityToken.getType().equals("profile")) {
				if (ProfileUtil.isAdmin()) {
					/* view for Profile */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewProfileEvent(entityToken
								.getId()));

					/* list for Profile */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						eventBus.fireEvent(new ListProfileEvent());
					}
					/* new Profile */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						eventBus.fireEvent(new CreateProfileEvent());
					}
				}
			}
			/** actions for CardEntity */
			if (entityToken.getType().equals("cardentity")) {
				if (ProfileUtil.isAdmin()) {
					/* view for CardEntity */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewCardEntityEvent(entityToken
								.getId()));

					/* list for CardEntity */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						eventBus.fireEvent(new ListCardEntityEvent());
					}
					/* new CardEntity */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						eventBus.fireEvent(new CreateCardEntityEvent());
					}
				}
			}
		} else
			eventBus.fireEvent(new GoHomeEvent());
	}

}
