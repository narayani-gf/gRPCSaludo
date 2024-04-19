package grpcholamundo.cliente;

import com.proto.saludo.SaludoServiceGrpc;
import com.proto.saludo.Holamundo.SaludoRequest;
import com.proto.saludo.Holamundo.SaludoResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Cliente {
    public static void main(String[] args) {
        // Establecemos el servidor gRPC
        String host = "localhost";
        // Establecemos el puerto gRPC
        int puerto = 8080;

        // Creamos el canal de comunicación
        ManagedChannel ch = ManagedChannelBuilder
            .forAddress(host, puerto)
            .usePlaintext()
            .build();

        // Obtenemos la referencia al stub
        SaludoServiceGrpc.SaludoServiceBlockingStub stub = SaludoServiceGrpc.newBlockingStub(ch);
        // Coonstruimos la petición enviando un parámetro
        SaludoRequest peticion = SaludoRequest.newBuilder().setNombre("Nara").build();
        // Usando el stub, realizamos la llamada gRPC
        SaludoResponse respuesta = stub.saludo(peticion);

        // Imprimimos la respuesta de gRPC
        System.out.println("Respuesta RPC: " + respuesta.getResultado());

        // Terminamos la comunicación
        System.out.println("Apagando...");
        ch.shutdown();
    }
}
