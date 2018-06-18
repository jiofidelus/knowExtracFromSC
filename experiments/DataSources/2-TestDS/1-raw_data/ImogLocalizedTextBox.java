package org.imogene.epicam.client.ui.field;

import java.util.List;

import org.imogene.epicam.shared.proxy.LocalizedTextProxy;
import org.imogene.web.client.event.FieldValueChangeEvent;
import org.imogene.web.client.ui.field.ImogField;
import org.imogene.web.client.ui.field.ImogFieldAbstract;
import org.imogene.web.client.ui.field.error.ImogErrorLabel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;

/**
 * Field Editor for Localized Text fields 
 * @author MEDES-IMPS
 */
public class ImogLocalizedTextBox extends Composite
		implements
			ImogField<LocalizedTextProxy>,
			LeafValueEditor<LocalizedTextProxy>,
			HasEditorErrors<LocalizedTextProxy> {

	private static final Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, ImogLocalizedTextBox> {
	}

	/* status - behavior */
	private boolean edited = false;
	private LocalizedTextProxy values;
	private String currentLocale;

	/* widgets */
	@UiField
	@Ignore
	ImogFieldAbstract fieldBox;
	@UiField
	ImogErrorLabel errorLabel;
	@UiField
	@Ignore
	TextBox textBox;
	@UiField(provided = true)
	ListBox localeListBox;

	/**
	 * Constructor
	 * @param isoCodes List of available locales (iso codes), for the application
	 * @param locale the selected locale for the field
	 */
	public ImogLocalizedTextBox(List<String> isoCodes, String locale) {

		currentLocale = locale;
		localeListBox = new ListBox();
		createLocaleList(isoCodes);

		initWidget(uiBinder.createAndBindUi(this));
	}

	/**
	 * Creates the Listbox that lists the available locales for
	 * the Localized TextBox
	 * @param isoCodes list of iso codes
	 */
	private void createLocaleList(List<String> isoCodes) {

		if (isoCodes != null) {
			int i = 0;
			for (String isoCode : isoCodes) {
				localeListBox.addItem(isoCode);
				// select current locale
				if (isoCode.equals(currentLocale))
					localeListBox.setSelectedIndex(i);
				i++;
			}
		}
	}

	@UiHandler("localeListBox")
	void onChangeLocale(ChangeEvent e) {

		// in edition mode, save previously entered text
		if (edited)
			storeText(currentLocale);

		// change text depending on locale
		currentLocale = localeListBox
				.getValue(localeListBox.getSelectedIndex());
		textBox.setText(getLocalizedText(currentLocale));
	}

	/**
	 * Gets the text corresponding to a given locale
	 * @param locale the locale for which the text has to be returned
	 * @return the text corresponding to a given locale
	 */
	private String getLocalizedText(String locale) {

		if (values != null) {

			if (locale.equals("fr"))
				return values.getFrancais();

			if (locale.equals("en"))
				return values.getEnglish();

			return "";
		}
		return "";
	}

	/**
	 * Tells if the box value is null
	 * @return
	 */
	private boolean isNull() {

		if ((values.getFrancais() == null || values.getFrancais().isEmpty())
				&& (values.getEnglish() == null || values.getEnglish()
						.isEmpty()))
			return true;
		else
			return false;
	}

	/**
	 * Stores the text that has been entered in the TextBox
	 * for a given locale in the LocalizedText
	 * @param locale the locale for which the text has to be stored
	 */
	private void storeText(String locale) {
		if (values != null) {

			if (locale.equals("fr"))
				values.setFrancais(textBox.getText());

			if (locale.equals("en"))
				values.setEnglish(textBox.getText());

		}
	}

	/**
	 * 
	 */
	public void setEdited(boolean enabled) {
		textBox.setEnabled(enabled);
		localeListBox.setEnabled(enabled);
		if (!enabled) {
			textBox.addStyleDependentName("disabled");
			localeListBox.addStyleDependentName("disabled");
		} else {
			textBox.removeStyleDependentName("disabled");
			localeListBox.removeStyleDependentName("disabled");
		}
		edited = enabled;
	}

	@Override
	public void setLabel(String label) {
		fieldBox.setLabel(label);
	}

	public void setLabel(String label, HorizontalAlignmentConstant alignment) {
		fieldBox.setLabel(label, alignment);
	}

	@Override
	public boolean isEdited() {
		return edited;
	}

	/**
	 */
	@Override
	public void setValue(LocalizedTextProxy value) {
		this.values = value;

		if (value != null) {

			// display the text for the current locale
			textBox.setText(getLocalizedText(currentLocale));

			// if no text is available for the current locale, get the first one that is available
			if (textBox.getText() == null || textBox.getText().equals("")) {
				for (int i = 0; i < localeListBox.getItemCount(); i++) {
					String locale = localeListBox.getValue(i);
					String text = getLocalizedText(locale);
					if (text != null && !text.equals("")) {
						textBox.setText(text);
						localeListBox.setSelectedIndex(i);
						currentLocale = locale;
						return;
					}
				}
			}
		}
	}

	/**
	 */
	@Override
	public LocalizedTextProxy getValue() {
		if (edited)
			storeText(currentLocale);

		if (isNull())
			return null;
		else
			return values;
	}

	/**
	 * Defines that the field shall notify value changes
	 * @param eventBus the event bus that will be used to fire the value change events
	 */
	public void notifyChanges(final EventBus eventBus) {
		if (eventBus != null) {
			textBox.addValueChangeHandler(new ValueChangeHandler<String>() {
				@Override
				public void onValueChange(ValueChangeEvent<String> arg0) {
					eventBus.fireEvent(new FieldValueChangeEvent(
							ImogLocalizedTextBox.this));
				}
			});
		}
	}

	@Override
	public void showErrors(List<EditorError> errors) {
		errorLabel.showErrors(errors);
	}

	public void setLabelWidth(String width) {
		fieldBox.setLabelWidth(width);
	}

	/**
	 * Sets the widget's width
	 */
	public void setBoxWidth(int width) {
		textBox.getElement().getStyle().setProperty("width", width + "px");
	}

}
