package org.atymelancholy.task3.hospital.state;

public sealed interface PatientState permits PatientState.Waiting, PatientState.InWard,
        PatientState.ReceivingMedication, PatientState.Treated {

    String label();

    final class Waiting implements PatientState {
        @Override
        public String label() {
            return "WAITING";
        }
    }

    final class InWard implements PatientState {
        @Override
        public String label() {
            return "IN_WARD";
        }
    }

    final class ReceivingMedication implements PatientState {
        @Override
        public String label() {
            return "RECEIVING_MEDICATION";
        }
    }

    final class Treated implements PatientState {
        @Override
        public String label() {
            return "TREATED";
        }
    }
}
