package io.example.domain;

import java.time.Instant;
import java.util.Optional;

import com.google.protobuf.Empty;
import com.google.protobuf.Timestamp;

import io.example.api.AppointmentApi;
import io.example.domain.AppointmentEntity.AppointmentState;
import kalix.javasdk.eventsourcedentity.EventSourcedEntityContext;

// This class was initially generated based on the .proto definition by Kalix tooling.
// This is the implementation for the Event Sourced Entity Service described in your io/example/api/appointment_api.proto file.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

public class Appointment extends AbstractAppointment {

  @SuppressWarnings("unused")
  private final String entityId;

  public Appointment(EventSourcedEntityContext context) {
    this.entityId = context.entityId();
  }

  @Override
  public AppointmentEntity.AppointmentState emptyState() {
    return AppointmentEntity.AppointmentState.getDefaultInstance();
  }

  @Override
  public Effect<Empty> createAppointment(AppointmentEntity.AppointmentState state, AppointmentApi.CreateAppointmentCommand command) {
    return reject(state, command).orElseGet(() -> handle(state, command));
  }

  @Override
  public Effect<Empty> updateAppointment(AppointmentEntity.AppointmentState state, AppointmentApi.UpdateAppointmentCommand command) {
    return reject(state, command).orElseGet(() -> handle(state, command));
  }

  @Override
  public Effect<Empty> cancelAppointment(AppointmentEntity.AppointmentState state, AppointmentApi.CancelAppointmentCommand command) {
    return reject(state, command).orElseGet(() -> handle(state, command));
  }

  @Override
  public Effect<AppointmentApi.GetAppointmentResponse> getAppointment(AppointmentEntity.AppointmentState state, AppointmentApi.GetAppointmentRequest request) {
    return reject(state, request).orElseGet(() -> handle(state, request));
  }

  @Override
  public AppointmentEntity.AppointmentState appointmentCreated(AppointmentEntity.AppointmentState state, AppointmentEntity.AppointmentCreated event) {
    return updateState(state, event);
  }

  @Override
  public AppointmentEntity.AppointmentState appointmentUpdated(AppointmentEntity.AppointmentState state, AppointmentEntity.AppointmentUpdated event) {
    return updateState(state, event);
  }

  @Override
  public AppointmentEntity.AppointmentState appointmentCanceled(AppointmentEntity.AppointmentState state, AppointmentEntity.AppointmentCanceled event) {
    return updateState(state, event);
  }

  private Optional<Effect<Empty>> reject(AppointmentEntity.AppointmentState state, AppointmentApi.CreateAppointmentCommand command) {
    if (state.getTime().getSeconds() > 0) {
      return Optional.of(effects().error(String.format("Appointment '%s' already exists", command.getAppointmentId())));
    }
    if (command.getOwnerId().isBlank()) {
      return Optional.of(effects().error("Owner Id is required"));
    }
    if (command.getVetId().isBlank()) {
      return Optional.of(effects().error("Vet Id is required"));
    }
    if (command.getDescription().isBlank()) {
      return Optional.of(effects().error("Description is required"));
    }
    return Optional.empty();
  }

  private Optional<Effect<Empty>> reject(AppointmentEntity.AppointmentState state, AppointmentApi.UpdateAppointmentCommand command) {
    if (state.getTime().getSeconds() == 0) {
      return Optional.of(effects().error(String.format("Appointment '%s' does not exist", command.getAppointmentId())));
    }
    if (state.getCanceledTime().getSeconds() > 0) {
      return Optional.of(effects().error(String.format("Appointment '%s' has been canceled", command.getAppointmentId())));
    }
    return Optional.empty();
  }

  private Optional<Effect<Empty>> reject(AppointmentEntity.AppointmentState state, AppointmentApi.CancelAppointmentCommand command) {
    if (state.getTime().getSeconds() == 0) {
      return Optional.of(effects().error(String.format("Appointment '%s' does not exist", command.getAppointmentId())));
    }
    return Optional.empty();
  }

  private Optional<Effect<AppointmentApi.GetAppointmentResponse>> reject(AppointmentEntity.AppointmentState state, AppointmentApi.GetAppointmentRequest request) {
    if (state.getTime().getSeconds() == 0) {
      return Optional.of(effects().error(String.format("Appointment '%s' does not exist", request.getAppointmentId())));
    }
    return Optional.empty();
  }

  private Effect<Empty> handle(AppointmentEntity.AppointmentState state, AppointmentApi.CreateAppointmentCommand command) {
    return effects()
        .emitEvent(eventFor(state, command))
        .thenReply(newState -> Empty.getDefaultInstance());
  }

  private Effect<Empty> handle(AppointmentEntity.AppointmentState state, AppointmentApi.UpdateAppointmentCommand command) {
    return effects()
        .emitEvent(eventFor(state, command))
        .thenReply(newState -> Empty.getDefaultInstance());
  }

  private Effect<Empty> handle(AppointmentEntity.AppointmentState state, AppointmentApi.CancelAppointmentCommand command) {
    return effects()
        .emitEvent(eventFor(state, command))
        .thenReply(newState -> Empty.getDefaultInstance());
  }

  private Effect<AppointmentApi.GetAppointmentResponse> handle(AppointmentEntity.AppointmentState state, AppointmentApi.GetAppointmentRequest request) {
    return effects().reply(toApi(state));
  }

  static AppointmentEntity.AppointmentCreated eventFor(AppointmentEntity.AppointmentState state, AppointmentApi.CreateAppointmentCommand command) {
    return AppointmentEntity.AppointmentCreated
        .newBuilder()
        .setAppointmentId(command.getAppointmentId())
        .setOwnerId(command.getOwnerId())
        .setVetId(command.getVetId())
        .setDescription(command.getDescription())
        .setTime(command.getTime())
        .build();
  }

  static AppointmentEntity.AppointmentUpdated eventFor(AppointmentEntity.AppointmentState state, AppointmentApi.UpdateAppointmentCommand command) {
    return AppointmentEntity.AppointmentUpdated
        .newBuilder()
        .setAppointmentId(command.getAppointmentId())
        .setOwnerId(command.getOwnerId())
        .setVetId(command.getVetId())
        .setDescription(command.getDescription())
        .setTime(command.getTime())
        .build();
  }

  static AppointmentEntity.AppointmentCanceled eventFor(AppointmentEntity.AppointmentState state, AppointmentApi.CancelAppointmentCommand command) {
    return AppointmentEntity.AppointmentCanceled
        .newBuilder()
        .setAppointmentId(command.getAppointmentId())
        .setCanceledTime(now())
        .build();
  }

  private static AppointmentApi.GetAppointmentResponse toApi(AppointmentState state) {
    return AppointmentApi.GetAppointmentResponse
        .newBuilder()
        .setAppointmentId(state.getAppointmentId())
        .setOwnerId(state.getOwnerId())
        .setVetId(state.getVetId())
        .setDescription(state.getDescription())
        .setTime(state.getTime())
        .setCanceledTime(state.getCanceledTime())
        .build();
  }

  static AppointmentEntity.AppointmentState updateState(AppointmentEntity.AppointmentState state, AppointmentEntity.AppointmentCreated event) {
    return state.toBuilder()
        .setAppointmentId(event.getAppointmentId())
        .setOwnerId(event.getOwnerId())
        .setVetId(event.getVetId())
        .setDescription(event.getDescription())
        .setTime(event.getTime())
        .build();
  }

  static AppointmentEntity.AppointmentState updateState(AppointmentEntity.AppointmentState state, AppointmentEntity.AppointmentUpdated event) {
    return state.toBuilder()
        .setOwnerId(event.getOwnerId().isBlank() ? state.getOwnerId() : event.getOwnerId())
        .setVetId(event.getVetId().isBlank() ? state.getVetId() : event.getVetId())
        .setDescription(event.getDescription().isBlank() ? state.getDescription() : event.getDescription())
        .setTime(event.getTime().getSeconds() == 0 ? state.getTime() : event.getTime())
        .build();
  }

  static AppointmentEntity.AppointmentState updateState(AppointmentEntity.AppointmentState state, AppointmentEntity.AppointmentCanceled event) {
    return state.toBuilder()
        .setCanceledTime(event.getCanceledTime())
        .build();
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
