package ventas;

import java.util.ArrayList;
import java.util.List;

public class ControlDeVentas {
    private List<Venta> registroDeVentas;

    public ControlDeVentas() {
        this.registroDeVentas = new ArrayList<>();
    }

    public List<Venta> getRegistroDeVentas() {
        return registroDeVentas;
    }

    public void setRegistroDeVentas(List<Venta> registroDeVentas) {
        this.registroDeVentas = registroDeVentas;
    }
}
