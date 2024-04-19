package grpcholamundo.servidor;


import com.proto.saludo.Holamundo.SaludoRequest;
import com.proto.saludo.Holamundo.SaludoResponse;
import com.proto.saludo.SaludoServiceGrpc;
import io.grpc.stub.StreamObserver;


public class ServidorImpl extends SaludoServiceGrpc.SaludoServiceImplBase {
    @Override
    public void saludo(SaludoRequest request, StreamObserver<SaludoResponse> responseObserver) {
        // Se construye la respuesta a enviarle al cliente
        SaludoResponse respuesta = SaludoResponse.newBuilder().setResultado("Hola " + request.getNombre()).build();

        // En gRPC se utiliza onNext para envar la respuesta
        // En llamadas unitarias, solo se llama una vez
        responseObserver.onNext(respuesta);

        // Avisa que se ha terminado
        responseObserver.onCompleted();
    }
}
