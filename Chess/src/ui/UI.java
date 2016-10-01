package ui;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import data.*;
import businessLogic.Functional;
import java.util.Arrays;

public class UI {
    private static String divisor = "-------------------";
    private static Scanner reader = new Scanner(System.in);
    
    public static void onWinMessage(Player player){
        System.out.println(divisor);
        System.out.println("\tEl jugador " + player.getName() + " ha ganado esta pertida");
        System.out.println(divisor);
    }
    
    public static void onTieMessage(Player[] player){
        System.out.println(divisor);
        System.out.println("\tlos jugadores " + player[0].getName() + "y" + 
                player[1].getName()+" han empatado");
        System.out.println(divisor);
    }
    
    public static void onQuitGame(Player player) {
        System.out.println(divisor);
        System.out.println("El jugador "+ player.getName() + "se ha retirado del juego");
        System.out.println(divisor);
    }
    
    public static void onError(){
        System.out.println(divisor);
        System.out.println("\tError!!, intente otra vez");
        System.out.println(divisor);
    }
    
    private static void onDuplicateCoordinate(){
        System.out.println(divisor);
        System.out.println("\tError!!, Cooerdenadas iguales, intente otra vez");
        System.out.println(divisor);
    }
    
    public static void onInvalidMove() {
        System.out.println(divisor);
        System.out.println("\tError!! Movimiento no valido, intente otra vez");
        System.out.println(divisor);
    }
    
    
    public static void printBoard(Board board){
        System.out.println("\n"+board.toString());
        System.out.println(divisor);
    }
    
    public static String readName(String col){
        System.out.println(divisor);
        System.out.println("Ingrese nombre del jugador "+ col);
        return reader.next();
    }

    public static void welcome() {
        System.out.println(divisor);
        System.out.println("Bienvenido al juego:!!");
    }
    
    public static int menu(){
        System.out.println(divisor);
        System.out.println("Seleccione una opcion");
        System.out.println("1. Iniciar juego");
        System.out.println("2. Salir");
        return reader.nextInt();
    }
    
    public static void printCemetery(Player w, Player b) {
        System.out.println(divisor);
        System.out.println("Cementerio: " );
        System.out.print("Blancas->" );
        for (int i=0;i<w.getCemetery().size();i++) {
            System.out.print(w.getCemetery().get(i).getPieceSign());
        }
        System.out.print("\nNegras->" );
        for (int i=0;i<b.getCemetery().size();i++) {
            System.out.print(b.getCemetery().get(i).getPieceSign());
        }
        System.out.println("");
    }
    
    
    public static void whosePlayer(Player player){
        String playerColor= (player.isColor())?"White":"Black";
        System.out.println(divisor);
        System.out.println("Turno del jugador " + player.getName() + " - " + playerColor);
    }
    

    private static String coordinateRead(){
        String moveText=new String();
        boolean flag=true; 
        do{
            moveText= reader.next();
            if(moveText.charAt(0)>='a' && moveText.charAt(0)<='h' && moveText.charAt(1)>='1' && moveText.charAt(1)<='8'){
                flag=false;
            }else{
                onError();
            }
        }while(flag);
        return moveText;
    }
    
    public static ArrayList<ArrayList<Integer>> inputMove() {
        boolean flag=true;
        ArrayList<ArrayList<Integer>> moveCoordinates= new ArrayList<>();
        
        do{
            System.out.println(divisor);
            System.out.println("Ingrese Coordenadas de la pieza, es decir letra y numero, por ejemplo ->e4");
            System.out.println("Nota: Para hacer enroque de coordenada del rey y de la torre");
            moveCoordinates.add(Functional.splitCoordinatesString(coordinateRead()));
            System.out.println("Ingrese Coordenadas de destino de la misma forma:");
            moveCoordinates.add(Functional.splitCoordinatesString(coordinateRead()));
            
            if(moveCoordinates.get(0).equals(moveCoordinates.get(1))){//to avoid same coordinates input
                onDuplicateCoordinate();
                moveCoordinates.clear();
            }else{
                flag=false;
            }
        }while(flag);
        
        return moveCoordinates;
    }

    public static Piece askPromotioPiece(boolean color) {
        Piece returnPiece;
        //create pieces to return
        ArrayList<Piece> values=new ArrayList<>(Arrays.asList(new Queen(color),
            new Knight(color),new Rook(color),new Bishop(color)));
        System.out.println(divisor);
        System.out.println("Seleccione pieza que quiere cambiar:");
        System.out.println("1. Queen");
        System.out.println("2. Knight");
        System.out.println("3. Rook");
        System.out.println("4. Bishop");
        
        while(true){
            int opt=reader.nextInt();
            if(opt>=1 && opt<=4){
                returnPiece= values.get(opt-1);
                break;
            }else{
                onError();
            }
        }
        return returnPiece;
    }

    public static int movementOptions() {
        int dataRead;
        
        
        System.out.println(divisor);
        System.out.println("Seleccione una opcion:");
        System.out.println("1. Realizar movimiento");
        System.out.println("2. Mostrar historial de jugadas (notacion LAN)");
        System.out.println("3. Retirarse");
        while(true){
            dataRead=reader.nextInt();
            if(dataRead>=1 && dataRead<=3){
                break;
            }else{
                onError();
            }
        }
        return dataRead;
    }

    public static void showPlayHist(Player[] player) {
        int it=0;
        System.out.println(divisor);
        System.out.println("Historial de Jugadas:");
        System.out.println("   Blancas \tNegras");
        while (true){
            System.out.print(it+". ");
            if(it<player[0].getHistory().size())System.out.print(player[0].getHistory().get(it)+"\t");
            else System.out.print("        "+"\t");
            
            if(it<player[1].getHistory().size())System.out.print(player[1].getHistory().get(it));
            else System.out.print("        ");
            System.out.println("");
            if(it>=player[0].getHistory().size() && it>=player[0].getHistory().size()){
                break;
            }
            it++;
        }
    }

    public static void castleFail() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
