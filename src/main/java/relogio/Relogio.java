package relogio;

import java.util.concurrent.TimeUnit;

public class Relogio {
    public static void main(String[] args) throws Exception {
        Contador horas = new Contador(2, 3, 0, 0);
        Contador minutos = new Contador(5, 9, 0, 0);
        Contador segundos = new Contador(5, 9, 0, 0);

        Display display = new Display();

        // Faz a operacão do relógio
        while(true){
            // Obtém os valores da dezena e unidade das horas e converte para bcd
            int[] valores = {horas.getDezena(), horas. getUnidade()};
            display.dec2bcd(valores);

            // Obtém os valores da dezena e unidade dos minutos e converte para bcd
            valores = new int[]{minutos.getDezena(), minutos.getUnidade()};
            display.dec2bcd(valores);

            // Obtém os valores da dezena e unidade dos segundos e converte para bcd
            valores = new int[]{segundos.getDezena(), segundos.getUnidade()};
            display.dec2bcd(valores);

            display.desenhaDisplays();

            TimeUnit.SECONDS.sleep(1);
            segundos.conta();

            // Se ocorrer um estouro da dezena dos segundos, conta um minuto
            if(segundos.isFlag()){
                minutos.conta();

                // Se ocorrer um estouro da dezena dos minutos, conta uma hora
                if(minutos.isFlag()){
                    horas.conta();
                }
            }
        }
    }
}