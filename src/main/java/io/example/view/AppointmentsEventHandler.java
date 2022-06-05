package io.example.view;

import io.example.domain.AppointmentEntity.AppointmentCanceled;
import io.example.domain.AppointmentEntity.AppointmentCreated;
import io.example.domain.AppointmentEntity.AppointmentState;
import io.example.domain.AppointmentEntity.AppointmentUpdated;

class AppointmentsEventHandler {

  static AppointmentState handle(AppointmentState state, AppointmentCreated event) {
    return AppointmentState
        .newBuilder()
        .setAppointmentId(event.getAppointmentId())
        .setOwnerId(event.getOwnerId())
        .setVetId(event.getVetId())
        .setDescription(event.getDescription())
        .setTime(event.getTime())
        .build();
  }

  static AppointmentState handle(AppointmentState state, AppointmentUpdated event) {
    return AppointmentState
        .newBuilder()
        .setOwnerId(event.getOwnerId().isBlank() ? state.getOwnerId() : event.getOwnerId())
        .setVetId(event.getVetId().isBlank() ? state.getVetId() : event.getVetId())
        .setDescription(event.getDescription().isBlank() ? state.getDescription() : event.getDescription())
        .setTime(event.getTime().getSeconds() == 0 ? state.getTime() : event.getTime())
        .build();
  }

  static AppointmentState handle(AppointmentState state, AppointmentCanceled event) {
    return AppointmentState
        .newBuilder()
        .setCanceledTime(event.getCanceledTime())
        .build();
  }
}
