package io.example.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Instant;

import org.junit.Ignore;
import org.junit.Test;

import com.google.protobuf.Timestamp;

import io.example.api.AppointmentApi;

// This class was initially generated based on the .proto definition by Kalix tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

public class AppointmentTest {

  @Test
  @Ignore("to be implemented")
  public void exampleTest() {
    // AppointmentTestKit service = AppointmentTestKit.of(Appointment::new);
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
  public void createAppointmentTest() {
    AppointmentTestKit service = AppointmentTestKit.of(Appointment::new);

    var command = AppointmentApi.CreateAppointmentCommand
        .newBuilder()
        .setAppointmentId("appointment-1")
        .setOwnerId("owner-1")
        .setVetId("vet-1")
        .setDescription("description-1")
        .setTime(now())
        .build();

    var result = service.createAppointment(command);

    assertTrue(result.isReply());

    var event = result.getNextEventOfType(AppointmentEntity.AppointmentCreated.class);

    assertEquals("appointment-1", event.getAppointmentId());
    assertEquals("owner-1", event.getOwnerId());
    assertEquals("vet-1", event.getVetId());
    assertEquals("description-1", event.getDescription());
    assertTrue(event.getTime().getSeconds() > 0);

    var state = service.getState();

    assertEquals("appointment-1", state.getAppointmentId());
    assertEquals("owner-1", state.getOwnerId());
    assertEquals("vet-1", state.getVetId());
    assertEquals("description-1", state.getDescription());
    assertTrue(state.getTime().getSeconds() > 0);

    var result2 = service.createAppointment(command);

    assertTrue(result2.isError()); // appointment already exists
  }

  @Test
  public void updateAppointmentTest() {
    AppointmentTestKit service = AppointmentTestKit.of(Appointment::new);

    var createCommand = AppointmentApi.CreateAppointmentCommand
        .newBuilder()
        .setAppointmentId("appointment-1")
        .setOwnerId("owner-1")
        .setVetId("vet-1")
        .setDescription("description-1")
        .setTime(now())
        .build();

    var createResult = service.createAppointment(createCommand);

    var createEvent = createResult.getNextEventOfType(AppointmentEntity.AppointmentCreated.class);

    var updateCommand = AppointmentApi.UpdateAppointmentCommand
        .newBuilder()
        .setAppointmentId("appointment-1")
        .setOwnerId("owner-2")
        .setVetId("vet-2")
        .setDescription("description-2")
        .setTime(now())
        .build();

    var updateResult = service.updateAppointment(updateCommand);

    assertTrue(updateResult.isReply());

    var updateEvent = updateResult.getNextEventOfType(AppointmentEntity.AppointmentUpdated.class);

    assertEquals("appointment-1", updateEvent.getAppointmentId());
    assertEquals("owner-2", updateEvent.getOwnerId());
    assertEquals("vet-2", updateEvent.getVetId());
    assertEquals("description-2", updateEvent.getDescription());
    assertTrue(updateEvent.getTime().getSeconds() > 0);
    assertTrue(updateEvent.getTime().getNanos() > createEvent.getTime().getNanos());

    var state = service.getState();

    assertEquals("appointment-1", state.getAppointmentId());
    assertEquals("owner-2", state.getOwnerId());
    assertEquals("vet-2", state.getVetId());
    assertEquals("description-2", state.getDescription());
    assertTrue(state.getTime().getSeconds() > 0);
    assertTrue(state.getTime().getNanos() > createEvent.getTime().getNanos());
  }

  @Test
  public void updateAppointmentTestNoFieldsUpdated() {
    AppointmentTestKit service = AppointmentTestKit.of(Appointment::new);

    var createCommand = AppointmentApi.CreateAppointmentCommand
        .newBuilder()
        .setAppointmentId("appointment-1")
        .setOwnerId("owner-1")
        .setVetId("vet-1")
        .setDescription("description-1")
        .setTime(now())
        .build();

    var createResult = service.createAppointment(createCommand);

    var createEvent = createResult.getNextEventOfType(AppointmentEntity.AppointmentCreated.class);

    var updateCommand = AppointmentApi.UpdateAppointmentCommand
        .newBuilder()
        .setAppointmentId("appointment-1")
        .build();

    var updateResult = service.updateAppointment(updateCommand);

    assertTrue(updateResult.isReply());

    updateResult.getNextEventOfType(AppointmentEntity.AppointmentUpdated.class);

    var state = service.getState();

    assertEquals("appointment-1", state.getAppointmentId());
    assertEquals("owner-1", state.getOwnerId());
    assertEquals("vet-1", state.getVetId());
    assertEquals("description-1", state.getDescription());
    assertTrue(state.getTime().getSeconds() > 0);
    assertEquals(state.getTime(), createEvent.getTime());
  }

  @Test
  public void cancelAppointmentTest() {
    AppointmentTestKit service = AppointmentTestKit.of(Appointment::new);

    var createCommand = AppointmentApi.CreateAppointmentCommand
        .newBuilder()
        .setAppointmentId("appointment-1")
        .setOwnerId("owner-1")
        .setVetId("vet-1")
        .setDescription("description-1")
        .setTime(now())
        .build();

    service.createAppointment(createCommand);

    var result = service.cancelAppointment(
        AppointmentApi.CancelAppointmentCommand
            .newBuilder()
            .setAppointmentId("appointment-1")
            .build());

    var event = result.getNextEventOfType(AppointmentEntity.AppointmentCanceled.class);

    assertEquals("appointment-1", event.getAppointmentId());
    assertTrue(event.getCanceledTime().getSeconds() > 0);

    var state = service.getState();

    assertTrue(state.getCanceledTime().getSeconds() > 0);
  }

  @Test
  public void getAppointmentTest() {
    AppointmentTestKit service = AppointmentTestKit.of(Appointment::new);

    var createCommand = AppointmentApi.CreateAppointmentCommand
        .newBuilder()
        .setAppointmentId("appointment-1")
        .setOwnerId("owner-1")
        .setVetId("vet-1")
        .setDescription("description-1")
        .setTime(now())
        .build();

    service.createAppointment(createCommand);

    var result = service.getAppointment(
        AppointmentApi.GetAppointmentRequest
            .newBuilder()
            .setAppointmentId("appointment-1")
            .build());

    assertTrue(result.isReply());

    var reply = result.getReply();

    assertEquals("appointment-1", reply.getAppointmentId());
    assertEquals("owner-1", reply.getOwnerId());
    assertEquals("vet-1", reply.getVetId());
    assertEquals("description-1", reply.getDescription());
    assertTrue(reply.getTime().getSeconds() > 0);
    assertTrue(reply.getCanceledTime().getSeconds() == 0);
  }

  private static Timestamp now() {
    var now = Instant.now();
    return Timestamp
        .newBuilder()
        .setSeconds(now.getEpochSecond())
        .setNanos(now.getNano())
        .build();
  }
}
