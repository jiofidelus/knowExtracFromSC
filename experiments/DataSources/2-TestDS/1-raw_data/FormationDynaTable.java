package org.imogene.epicam.client.ui.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.event.create.CreateFormationEvent;
import org.imogene.epicam.client.event.list.ListFormationEvent;
import org.imogene.epicam.client.event.view.ViewFormationEvent;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.filter.FormationFilterPanel;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.FormationProxy;
import org.imogene.epicam.shared.proxy.LocalizedTextProxy;
import org.imogene.epicam.shared.request.FormationRequest;
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

/**
 * Composite that displays the list of Formation entries
 * @author MEDES-IMPS
 */
public class FormationDynaTable extends ImogDynaTable<FormationProxy> {

	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();

	private PushButton deleteButton;

	public FormationDynaTable(EpicamRequestFactory requestFactory,
			ImogBeanDataProvider<FormationProxy> provider,
			boolean checkBoxesVisible) {
		super(requestFactory, provider, checkBoxesVisible);
	}

	public ImogFilterPanel getFilterPanel() {
		ImogFilterPanel filterPanel = new FormationFilterPanel();
		super.configureFilterPanel(filterPanel);
		return filterPanel;
	}

	/**
	 * 
	 */
	@Override
	protected void setColumns() {

		if (AccessManager.canReadFormationDescription()) {
			Column<FormationProxy, String> dateDebutColumn = new DateDebutColumn();
			dateDebutColumn.setSortable(true);
			table.addColumn(dateDebutColumn, NLS.constants()
					.formation_field_s_dateDebut());
		}
		if (AccessManager.canReadFormationDescription()) {
			Column<FormationProxy, String> libelleColumn = new LibelleColumn();
			libelleColumn.setSortable(true);
			table.addColumn(libelleColumn, NLS.constants()
					.formation_field_s_libelle());
		}
		if (AccessManager.canReadFormationDescription()) {
			Column<FormationProxy, String> lieuColumn = new LieuColumn();
			lieuColumn.setSortable(true);
			table.addColumn(lieuColumn, NLS.constants()
					.formation_field_s_lieu());
		}
		if (AccessManager.canReadFormationDescription()) {
			Column<FormationProxy, String> effectueeColumn = new EffectueeColumn();
			effectueeColumn.setSortable(true);
			table.addColumn(effectueeColumn, NLS.constants()
					.formation_field_s_effectuee());
		}

	}

	@Override
	protected GwtEvent<?> getViewEvent(FormationProxy value) {
		History.newItem(TokenHelper.TK_VIEW + "/formation/" + value.getId(),
				true);
		return null;
	}

	@Override
	protected String getDefaultSortProperty() {
		return "dateDebut";
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

		if (AccessManager.canCreateFormation()
				&& AccessManager.canEditFormation()) {
			Command command = new Command() {
				public void execute() {
					History.newItem(TokenHelper.TK_NEW + "/formation/", true);
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

		if (AccessManager.canDeleteFormation()
				&& AccessManager.canEditFormation()) {
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

		if (AccessManager.canDeleteFormation()
				&& AccessManager.canEditFormation()) {

			// Click handler
			registrations.add(deleteButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {

					Set<FormationProxy> selectedEntities = selectionModel
							.getSelectedSet();

					int count = selectedEntities.size();
					if (count > 0) {

						EpicamRenderer renderer = EpicamRenderer.get();

						StringBuffer msg = new StringBuffer();
						msg.append(BaseNLS.constants()
								.confirmation_delete_several1()
								+ " "
								+ NLS.constants().formation_name()
								+ " "
								+ BaseNLS.constants()
										.confirmation_delete_several2() + ": ");
						int i = 0;
						for (FormationProxy entity : selectedEntities) {
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

							Request<Void> deleteRequest = getFormationRequest()
									.delete(selectedEntities);
							deleteRequest.fire(new Receiver<Void>() {
								@Override
								public void onSuccess(Void response) {
									//Window.alert("The selected Formation entries have been deleted");
									requestFactory.getEventBus().fireEvent(
											new ListFormationEvent());
								}

								@Override
								public void onFailure(ServerFailure error) {
									Window.alert("Error deleting the Formation entries");
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
	 * Creates the action command that enables to export the Formation
	 * entries in a csv file
	 * @return the command
	 */
	public Command getCsvExportButton() {

		if (AccessManager.canExportFormation()) {

			Command command = new Command() {
				public void execute() {

					String url = GWT.getHostPageBaseURL()
							+ EpicamBirtConstants.FORM_CSV_KEY + "?"
							+ EpicamBirtConstants.REPORT_NAME
							+ "=formation_csv" + "&"
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

	private FormationRequest getFormationRequest() {
		EpicamRequestFactory epicamRequestFactory = (EpicamRequestFactory) requestFactory;
		return epicamRequestFactory.formationRequest();
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
	 * Column for field DateDebut
	 * @author MEDES-IMPS
	 */
	private class DateDebutColumn extends ImogColumn<FormationProxy, String> {

		public DateDebutColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(FormationProxy object) {
			String value = null;
			if (object != null) {
				if (object.getDateDebut() == null)
					value = "";
				else
					value = DateUtil.getFormatedDate(object.getDateDebut());
			}
			return value;
		}

		public String getPropertyName() {
			return "dateDebut";
		}
	}

	/**
	 * Column for field Libelle
	 * @author MEDES-IMPS
	 */
	private class LibelleColumn extends ImogColumn<FormationProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public LibelleColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(FormationProxy object) {
			String value = null;
			if (object != null) {
				if (object.getLibelle() == null)
					value = "";
				else
					value = renderer.getLocalizedText(object.getLibelle());
			}
			return value;
		}

		public String getPropertyName() {
			String locale = NLS.constants().locale();
			if (locale.equals("fr"))
				return "libelle.francais";
			if (locale.equals("en"))
				return "libelle.english";
			return "libelle";
		}
	}

	/**
	 * Column for field Lieu
	 * @author MEDES-IMPS
	 */
	private class LieuColumn extends ImogColumn<FormationProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public LieuColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(FormationProxy object) {
			String value = null;
			if (object != null) {
				if (object.getLieu() == null)
					value = "";
				else
					value = renderer.getLocalizedText(object.getLieu());
			}
			return value;
		}

		public String getPropertyName() {
			String locale = NLS.constants().locale();
			if (locale.equals("fr"))
				return "lieu.francais";
			if (locale.equals("en"))
				return "lieu.english";
			return "lieu";
		}
	}

	/**
	 * Column for field Effectuee
	 * @author MEDES-IMPS
	 */
	private class EffectueeColumn extends ImogColumn<FormationProxy, String> {

		public EffectueeColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(FormationProxy object) {
			String value = null;
			if (object != null) {
				if (object.getEffectuee() == null)
					value = "";
				else
					value = BooleanUtil.getBooleanDisplayValue(object
							.getEffectuee());
			}
			return value;
		}

		public String getPropertyName() {
			return "effectuee";
		}
	}

}
