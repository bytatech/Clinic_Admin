import { BaseEntity } from './../../shared';

export class ClinicPatientPrivateData implements BaseEntity {
    constructor(
        public id?: number,
        public patientId?: number,
        public address?: string,
        public city?: string,
        public zip?: number,
        public email?: string,
        public country?: string,
        public socialMediaId?: string,
        public profession?: string,
    ) {
    }
}
