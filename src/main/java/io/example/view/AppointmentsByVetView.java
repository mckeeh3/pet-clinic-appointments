package io.example.view;

import io.example.domain.AppointmentEntity.*;
import kalix.javasdk.view.View;
import kalix.javasdk.view.ViewContext;

// This class was initially generated based on the .proto definition by Kalix tooling.
// This is the implementation for the View Service described in your io/example/view/appointments_by_vet.proto file.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

public class AppointmentsByVetView extends AbstractAppointmentsByVetView {

  public AppointmentsByVetView(ViewContext context) {
  }

  @Override
  public AppointmentState emptyState() {
    return AppointmentState.getDefaultInstance();
  }

  @Override
  public View.UpdateEffect<AppointmentState> onAppointmentCreated(AppointmentState state, AppointmentCreated event) {
    return effects().updateState(AppointmentsEventHandler.handle(state, event));
  }

  @Override
  public View.UpdateEffect<AppointmentState> onAppointmentUpdated(AppointmentState state, AppointmentUpdated event) {
    return effects().updateState(AppointmentsEventHandler.handle(state, event));
  }

  @Override
  public View.UpdateEffect<AppointmentState> onAppointmentCanceled(AppointmentState state, AppointmentCanceled event) {
    return effects().updateState(AppointmentsEventHandler.handle(state, event));
  }
}
