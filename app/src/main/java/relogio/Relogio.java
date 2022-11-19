package relogio;

public class Relogio {
    public static void main(String[] args) {
        Contador horas = new Contador(9, 9, 0, 0);
        Contador minutos = new Contador(2, 3, 0, 0);
        Contador segundos = new Contador(2, 3, 0, 0);

        while(true){
            segundos.conta();

            if(segundos.isFlag()){
                minutos.conta();

                if(minutos.isFlag()){
                    horas.conta();
                }
            }
        }
    }
}
