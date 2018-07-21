package br.com.sga.view;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.sql.Date;
import java.time.LocalDate;

import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;

/**
 * Create an anchor pane that can store additional data.
 */
public class AnchorPaneDate extends AnchorPane {

    // Date associated with this pane
    private LocalDate date;
    private Text text;

    /**
     * Create a anchor pane node. Date is not assigned in the constructor.
     * @param children children of the anchor pane
     */
    public AnchorPaneDate(Node... children) {
        super(children);
        
        setPrefSize(200,200);
        setStyle("-fx-background-color: #DCDCDC;");
        text = new Text("Vazio");
        
		text.setFont(new Font("Verdana", 13));
		text.setFill(Paint.valueOf("black"));
		text.setVisible(true);
//		setTopAnchor(text, 5.0);
//		setLeftAnchor(text, 5.0);
		
        this.setOnMouseClicked(e -> {
			try {
				System.out.println(date.toString());
				Dialogo.getInstance().DetalhesData(date, Fachada.getInstance().buscarNotificacaoPorData(Date.valueOf(date)));
			} catch (BusinessException e1) {
				Alerta.getInstance().showMensagem("Erro!", "", e1.getMessage());
				e1.printStackTrace();
			}
		});        
        
        getChildren().add(text);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

	public Text getText() {
		System.out.println(text.getText());
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}
}
