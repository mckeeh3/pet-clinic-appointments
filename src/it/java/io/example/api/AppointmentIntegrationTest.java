package io.example.api;

import com.google.protobuf.Empty;
import io.example.Main;
import io.example.domain.AppointmentEntity;
import kalix.javasdk.testkit.junit.KalixTestKitResource;
import org.junit.ClassRule;
import org.junit.Test;

import static java.util.concurrent.TimeUnit.*;

// This class was initially generated based on the .proto definition by Kalix tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

// Example of an integration test calling our service via the Kalix proxy
// Run all test classes ending with "IntegrationTest" using `mvn verify -Pit`
public class AppointmentIntegrationTest {

  /**
   * The test kit starts both the service container and the Kalix proxy.
   */
  @ClassRule
  public static final KalixTestKitResource testKit =
    new KalixTestKitResource(Main.createKalix());

  /**
   * Use the generated gRPC client to call the service through the Kalix proxy.
   */
  private final AppointmentService client;

  public AppointmentIntegrationTest() {
    client = testKit.getGrpcClient(AppointmentService.class);
  }

  @Test
  public void createAppointmentOnNonExistingEntity() throws Exception {
    // TODO: set fields in command, and provide assertions to match replies
    // client.createAppointment(AppointmentApi.CreateAppointmentCommand.newBuilder().build())
    //         .toCompletableFuture().get(5, SECONDS);
  }

  @Test
  public void updateAppointmentOnNonExistingEntity() throws Exception {
    // TODO: set fields in command, and provide assertions to match replies
    // client.updateAppointment(AppointmentApi.UpdateAppointmentCommand.newBuilder().build())
    //         .toCompletableFuture().get(5, SECONDS);
  }

  @Test
  public void cancelAppointmentOnNonExistingEntity() throws Exception {
    // TODO: set fields in command, and provide assertions to match replies
    // client.cancelAppointment(AppointmentApi.CancelAppointmentCommand.newBuilder().build())
    //         .toCompletableFuture().get(5, SECONDS);
  }

  @Test
  public void getAppointmentOnNonExistingEntity() throws Exception {
    // TODO: set fields in command, and provide assertions to match replies
    // client.getAppointment(AppointmentApi.GetAppointmentRequest.newBuilder().build())
    //         .toCompletableFuture().get(5, SECONDS);
  }
}
