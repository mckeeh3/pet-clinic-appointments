package io.example.domain;

import com.google.protobuf.Empty;
import io.example.api.AppointmentApi;
import kalix.javasdk.eventsourcedentity.EventSourcedEntity;
import kalix.javasdk.eventsourcedentity.EventSourcedEntityContext;
import kalix.javasdk.testkit.EventSourcedResult;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

// This class was initially generated based on the .proto definition by Kalix tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

public class AppointmentTest {

  @Test
  @Ignore("to be implemented")
  public void exampleTest() {
    AppointmentTestKit service = AppointmentTestKit.of(Appointment::new);
    // // use the testkit to execute a command
    // // of events emitted, or a final updated state:
    // SomeCommand command = SomeCommand.newBuilder()...build();
    // EventSourcedResult<SomeResponse> result = service.someOperation(command);
    // // verify the emitted events
    // ExpectedEvent actualEvent = result.getNextEventOfType(ExpectedEvent.class);
    // assertEquals(expectedEvent, actualEvent);
    // // verify the final state after applying the events
    // assertEquals(expectedState, service.getState());
    // // verify the reply
    // SomeReply reply = result.getReply();
    // assertEquals(expectedReply, reply);
  }

  @Test
  @Ignore("to be implemented")
  public void createAppointmentTest() {
    AppointmentTestKit service = AppointmentTestKit.of(Appointment::new);
    // CreateAppointmentCommand command = CreateAppointmentCommand.newBuilder()...build();
    // EventSourcedResult<Empty> result = service.createAppointment(command);
  }


  @Test
  @Ignore("to be implemented")
  public void updateAppointmentTest() {
    AppointmentTestKit service = AppointmentTestKit.of(Appointment::new);
    // UpdateAppointmentCommand command = UpdateAppointmentCommand.newBuilder()...build();
    // EventSourcedResult<Empty> result = service.updateAppointment(command);
  }


  @Test
  @Ignore("to be implemented")
  public void cancelAppointmentTest() {
    AppointmentTestKit service = AppointmentTestKit.of(Appointment::new);
    // CancelAppointmentCommand command = CancelAppointmentCommand.newBuilder()...build();
    // EventSourcedResult<Empty> result = service.cancelAppointment(command);
  }


  @Test
  @Ignore("to be implemented")
  public void getAppointmentTest() {
    AppointmentTestKit service = AppointmentTestKit.of(Appointment::new);
    // GetAppointmentRequest command = GetAppointmentRequest.newBuilder()...build();
    // EventSourcedResult<GetAppointmentResponse> result = service.getAppointment(command);
  }

}
