package org.imogene.epicam.client.ui.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.event.create.CreatePersonnelEvent;
import org.imogene.epicam.client.event.list.ListPersonnelEvent;
import org.imogene.epicam.client.event.view.ViewPersonnelEvent;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.filter.PersonnelFilterPanel;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.PersonnelProxy;
import org.imogene.epicam.shared.proxy.LocalizedTextProxy;
import org.imogene.epicam.shared.request.PersonnelRequest;
import org.imogene.epicam.client.i18n.NLS;
import org.imogene.web.client.event.SelectionChangedInTableEvent;
import org.imogene.web.client.i18n.BaseNLS;
import org.imogene.web.client.ui.table.ImogBeanDataProvider;
import org.imogene.web.client.ui.table.ImogColumn;
import org.imogene.web.client.ui.table.ImogDynaTable;
import org.imogene.web.client.ui.table.filter.ImogFilterPanel;
import org.imogene.web.client.util.BooleanUtil;
import org.imogene.web.client.util.DateUtil;
import org.imogene.web.client.util.TokenHelper;
import org.imogene.epicam.shared.constants.EpicamBirtConstants;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.PushButton;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import org.imogene.admin.client.i18n.AdminNLS;
import org.imogene.web.client.util.ProfileUtil;

/**
 * Composite that displays the list of Personnel entries
 * @author MEDES-IMPS
 */
public class PersonnelDynaTable extends ImogDynaTable<PersonnelProxy> {

	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();

	private PushButton deleteButton;

	public PersonnelDynaTable(EpicamRequestFactory requestFactory,
			ImogBeanDataProvider<PersonnelProxy> provider,
			boolean checkBoxesVisible) {
		super(requestFactory, provider, checkBoxesVisible);
	}

	public ImogFilterPanel getFilterPanel() {
		ImogFilterPanel filterPanel = new PersonnelFilterPanel();
		super.configureFilterPanel(filterPanel);
		return filterPanel;
	}

	/**
	 * 
	 */
	@Override
	protected void setColumns() {

		if (AccessManager.canReadPersonnelIdentification()) {
			Column<PersonnelProxy, String> nomColumn = new NomColumn();
			nomColumn.setSortable(true);
			table.addColumn(nomColumn, NLS.constants().personnel_field_s_nom());
		}
		if (AccessManager.canReadPersonnelIdentification()) {
			Column<PersonnelProxy, String> dateNaissanceColumn = new DateNaissanceColumn();
			dateNaissanceColumn.setSortable(true);
			table.addColumn(dateNaissanceColumn, NLS.constants()
					.personnel_field_s_dateNaissance());
		}
		if (AccessManager.canReadPersonnelSituation()) {
			Column<PersonnelProxy, String> dateArriveeColumn = new DateArriveeColumn();
			dateArriveeColumn.setSortable(true);
			table.addColumn(dateArriveeColumn, NLS.constants()
					.personnel_field_s_dateArrivee());
		}
		if (AccessManager.canReadPersonnelSituation()) {
			Column<PersonnelProxy, String> dateDepartColumn = new DateDepartColumn();
			dateDepartColumn.setSortable(true);
			table.addColumn(dateDepartColumn, NLS.constants()
					.personnel_field_s_dateDepart());
		}
		if (AccessManager.canReadPersonnelNiveauAccess()) {
			Column<PersonnelProxy, String> niveauColumn = new NiveauColumn();
			niveauColumn.setSortable(true);
			table.addColumn(niveauColumn, NLS.constants()
					.personnel_field_s_niveau());
		}
		if (AccessManager.canReadPersonnelNiveauAccess()) {
			Column<PersonnelProxy, String> actifColumn = new ActifColumn();
			actifColumn.setSortable(true);
			table.addColumn(actifColumn, NLS.constants()
					.personnel_field_s_actif());
		}

		if (ProfileUtil.isAdmin()) {
			Column<PersonnelProxy, String> loginColumn = new LoginColumn();
			loginColumn.setSortable(true);
			table.addColumn(loginColumn, AdminNLS.constants()
					.imogActor_field_s_login());
		}
	}

	@Override
	protected GwtEvent<?> getViewEvent(PersonnelProxy value) {
		History.newItem(TokenHelper.TK_VIEW + "/personnel/" + value.getId(),
				true);
		return null;
	}

	@Override
	protected String getDefaultSortProperty() {
		return "modified";
	}

	@Override
	protected boolean getDefaultSortPropertyOrder() {
		return false;
	}

	/**
	 * Creates the Create action command for the entity
	 * @return the create command
	 */
	public Command getCreateCommand() {

		if (AccessManager.canCreatePersonnel()
				&& AccessManager.canEditPersonnel()) {
			Command command = new Command() {
				public void execute() {
					History.newItem(TokenHelper.TK_NEW + "/personnel/", true);
				}
			};
			return command;
		} else
			return null;
	}

	/**
	 * Creates the Delete action command for the entity
	 * @return the delete command
	 */
	public PushButton getDeleteButton() {

		if (AccessManager.canDeletePersonnel()
				&& AccessManager.canEditPersonnel()) {
			deleteButton = new PushButton(BaseNLS.constants().button_delete());
			deleteButton.setStyleName(imogResources.imogStyle().imogButton());
			deleteButton.addStyleName("Dynatable-Button");
			deleteButton.setVisible(false);
			return deleteButton;
		}

		return null;
	}

	/**
	 * Creates the Handlers linked to the delete button
	 */
	private void setDeleteButtonHandlers() {

		if (AccessManager.canDeletePersonnel()
				&& AccessManager.canEditPersonnel()) {

			// Click handler
			registrations.add(deleteButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {

					Set<PersonnelProxy> selectedEntities = selectionModel
							.getSelectedSet();

					int count = selectedEntities.size();
					if (count > 0) {

						EpicamRenderer renderer = EpicamRenderer.get();

						StringBuffer msg = new StringBuffer();
						msg.append(BaseNLS.constants()
								.confirmation_delete_several1()
								+ " "
								+ NLS.constants().personnel_name()
								+ " "
								+ BaseNLS.constants()
										.confirmation_delete_several2() + ": ");
						int i = 0;
						for (PersonnelProxy entity : selectedEntities) {
							if (count == 1 || i == count - 1)
								msg.append("'"
										+ renderer.getDisplayValue(entity)
										+ "' ?");
							else
								msg.append("'"
										+ renderer.getDisplayValue(entity)
										+ "', ");
							i = i + 1;
						}

						boolean toDelete = Window.confirm(msg.toString());
						if (toDelete) {

							Request<Void> deleteRequest = getPersonnelRequest()
									.delete(selectedEntities);
							deleteRequest.fire(new Receiver<Void>() {
								@Override
								public void onSuccess(Void response) {
									//Window.alert("The selected Personnel entries have been deleted");
									requestFactory.getEventBus().fireEvent(
											new ListPersonnelEvent());
								}

								@Override
								public void onFailure(ServerFailure error) {
									Window.alert("Error deleting the Personnel entries");
									super.onFailure(error);
								}
							});
						}
					}

				}
			}));

			// Selection changed handler	
			registrations.add(requestFactory.getEventBus().addHandler(
					SelectionChangedInTableEvent.TYPE,
					new SelectionChangedInTableEvent.Handler() {
						@Override
						public void noticeSelectionChange(int selectedItems) {
							if (selectedItems > 0)
								deleteButton.setVisible(true);
							else
								deleteButton.setVisible(false);
						}
					}));
		}
	}

	/**
	 * Creates the action command that enables to export the Personnel
	 * entries in a csv file
	 * @return the command
	 */
	public Command getCsvExportButton() {

		if (AccessManager.canExportPersonnel()) {

			Command command = new Command() {
				public void execute() {

					String url = GWT.getHostPageBaseURL()
							+ EpicamBirtConstants.PERS_CSV_KEY + "?"
							+ EpicamBirtConstants.REPORT_NAME
							+ "=personnel_csv" + "&"
							+ EpicamBirtConstants.REPORT_LOCALE + "="
							+ NLS.constants().locale() + "&"
							+ EpicamBirtConstants.REPORT_FORMAT + "="
							+ EpicamBirtConstants.CSV;

					if (beanDataProvider.getSearchCriterions() != null)
						url = url + getDataProviderCriteria();

					Window.open(url, "_blank", "");
				}
			};
			return command;

		} else
			return null;
	}

	private PersonnelRequest getPersonnelRequest() {
		EpicamRequestFactory epicamRequestFactory = (EpicamRequestFactory) requestFactory;
		return epicamRequestFactory.personnelRequest();
	}

	@Override
	protected void onUnload() {
		for (HandlerRegistration r : registrations)
			r.removeHandler();
		registrations.clear();
		super.onUnload();
	}

	@Override
	protected void onLoad() {
		setDeleteButtonHandlers();
		super.onLoad();
	}

	/**
	 * --------------------- * Internal classes * ----------------------
	 */

	/**
	 * Column for field Nom
	 * @author MEDES-IMPS
	 */
	private class NomColumn extends ImogColumn<PersonnelProxy, String> {

		public NomColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(PersonnelProxy object) {
			String value = null;
			if (object != null) {
				if (object.getNom() == null)
					value = "";
				else
					value = object.getNom();
			}
			return value;
		}

		public String getPropertyName() {
			return "nom";
		}
	}

	/**
	 * Column for field DateNaissance
	 * @author MEDES-IMPS
	 */
	private class DateNaissanceColumn
			extends
				ImogColumn<PersonnelProxy, String> {

		public DateNaissanceColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(PersonnelProxy object) {
			String value = null;
			if (object != null) {
				if (object.getDateNaissance() == null)
					value = "";
				else
					value = DateUtil.getFormatedDate(object.getDateNaissance());
			}
			return value;
		}

		public String getPropertyName() {
			return "dateNaissance";
		}
	}

	/**
	 * Column for field DateArrivee
	 * @author MEDES-IMPS
	 */
	private class DateArriveeColumn extends ImogColumn<PersonnelProxy, String> {

		public DateArriveeColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(PersonnelProxy object) {
			String value = null;
			if (object != null) {
				if (object.getDateArrivee() == null)
					value = "";
				else
					value = DateUtil.getFormatedDate(object.getDateArrivee());
			}
			return value;
		}

		public String getPropertyName() {
			return "dateArrivee";
		}
	}

	/**
	 * Column for field DateDepart
	 * @author MEDES-IMPS
	 */
	private class DateDepartColumn extends ImogColumn<PersonnelProxy, String> {

		public DateDepartColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(PersonnelProxy object) {
			String value = null;
			if (object != null) {
				if (object.getDateDepart() == null)
					value = "";
				else
					value = DateUtil.getFormatedDate(object.getDateDepart());
			}
			return value;
		}

		public String getPropertyName() {
			return "dateDepart";
		}
	}

	/**
	 * Column for field Niveau
	 * @author MEDES-IMPS
	 */
	private class NiveauColumn extends ImogColumn<PersonnelProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public NiveauColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(PersonnelProxy object) {
			String value = null;
			if (object != null) {
				if (object.getNiveau() == null)
					value = "";
				else
					value = renderer.getEnumDisplayValue(PersonnelProxy.class,
							"niveau", object.getNiveau());
			}
			return value;
		}

		public String getPropertyName() {
			return "niveau";
		}
	}

	/**
	 * Column for field Actif
	 * @author MEDES-IMPS
	 */
	private class ActifColumn extends ImogColumn<PersonnelProxy, String> {

		public ActifColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(PersonnelProxy object) {
			String value = null;
			if (object != null) {
				if (object.getActif() == null)
					value = "";
				else
					value = BooleanUtil.getBooleanDisplayValue(object
							.getActif());
			}
			return value;
		}

		public String getPropertyName() {
			return "actif";
		}
	}

	/**
	 * Column for field Login
	 * @author MEDES-IMPS
	 */
	private class LoginColumn extends ImogColumn<PersonnelProxy, String> {

		public LoginColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(PersonnelProxy object) {
			String value = null;
			if (object.getLogin() == null)
				value = "";
			else
				value = object.getLogin();

			return value;
		}

		public String getPropertyName() {
			return "login";
		}
	}

}
