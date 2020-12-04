package ehu.isad.Model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import ehu.isad.Controllers.DB.CMSDBKud;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;

public class DatePickerCell<S, T> extends TableCell<Eskaneoa, LocalDate> {

    private DatePicker datePicker;
    private ObservableList<Eskaneoa> eskaneoa;

    public DatePickerCell(ObservableList<Eskaneoa> eskaneoa) {

        super();

        this.eskaneoa = eskaneoa;

        if (datePicker == null) {
            createDatePicker();
        }
        setGraphic(datePicker);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                datePicker.requestFocus();
            }
        });
    }

    @Override
    public void updateItem(LocalDate item, boolean empty) {

        super.updateItem(item, empty);

        SimpleDateFormat smp = new SimpleDateFormat("dd/MM/yyyy");

        if (null == this.datePicker) {
            System.out.println("datePicker is NULL");
        }

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {

            if (isEditing()) {
                setContentDisplay(ContentDisplay.TEXT_ONLY);

            } else {

                // https://stackoverflow.com/a/40143687/243532
                Date date = java.util.Date.from(                     // Convert from modern java.time class to troublesome old legacy class.  DO NOT DO THIS unless you must, to inter operate with old code not yet updated for java.time.
                        item                          // `LocalDate` class represents a date-only, without time-of-day and without time zone nor offset-from-UTC.
                                .atStartOfDay(                       // Let java.time determine the first moment of the day on that date in that zone. Never assume the day starts at 00:00:00.
                                        ZoneId.systemDefault()  // Specify time zone using proper name in `continent/region` format, never 3-4 letter pseudo-zones such as “PST”, “CST”, “IST”.
                                )                                    // Produce a `ZonedDateTime` object.
                                .toInstant()                         // Extract an `Instant` object, a moment always in UTC.
                );

                setDatepikerDate(smp.format(date));
                setText(smp.format(date));
                setGraphic(this.datePicker);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }
        }
    }

    private void setDatepikerDate(String dateAsStr) {

        LocalDate ld = null;
        int jour, mois, annee;

        jour = mois = annee = 0;
        try {
            jour = Integer.parseInt(dateAsStr.substring(0, 2));
            mois = Integer.parseInt(dateAsStr.substring(3, 5));
            annee = Integer.parseInt(dateAsStr.substring(6, dateAsStr.length()));
        } catch (NumberFormatException e) {
            System.out.println("setDatepikerDate / unexpected error " + e);
        }

        ld = LocalDate.of(annee, mois, jour);
        datePicker.setValue(ld);
    }

    private void createDatePicker() {
        this.datePicker = new DatePicker();
        datePicker.setPromptText("jj/mm/aaaa");
        datePicker.setEditable(true);

        datePicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                LocalDate date = datePicker.getValue();
                int index = getIndex();

                SimpleDateFormat smp = new SimpleDateFormat("dd/MM/yyyy");
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.DAY_OF_MONTH, date.getDayOfMonth());
                cal.set(Calendar.MONTH, date.getMonthValue() - 1);
                cal.set(Calendar.YEAR, date.getYear());

                setText(smp.format(cal.getTime()));

                // https://stackoverflow.com/a/54251505/243532
                LocalDate ld = LocalDateTime.ofInstant(cal.toInstant(), cal.getTimeZone().toZoneId()).toLocalDate();
                commitEdit(ld);

                if (null != getEskaneoa()) {

                    getEskaneoa().get(index).setLastupdated(ld);
                    CMSDBKud.getInstance().dataEguneratu( getEskaneoa().get(index).getUrl(),ld);

                }
            }
        });

        setAlignment(Pos.CENTER);
    }

    @Override
    public void startEdit() {
        super.startEdit();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

    public ObservableList<Eskaneoa> getEskaneoa() {
        return eskaneoa;
    }

    public void setWebgunea(ObservableList<Eskaneoa> webgunea) {
        this.eskaneoa = webgunea;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public void setDatePicker(DatePicker datePicker) {
        this.datePicker = datePicker;
    }

}