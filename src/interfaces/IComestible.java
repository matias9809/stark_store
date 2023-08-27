package interfaces;

import java.time.LocalDate;
import java.util.Date;

public interface IComestible {
    void setFechaVencimiento(LocalDate fechaVencimiento);
    LocalDate getFechaVencimiento();
    void setCalorias(double calorias);
    double getCalorias();

}
