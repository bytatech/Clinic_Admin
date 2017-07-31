import { BaseEntity } from './../../shared';

export class Prescription implements BaseEntity {
    constructor(
        public id?: number,
        public date?: any,
        public doctorName?: string,
        public clinicPatientData?: BaseEntity,
        public drugs?: BaseEntity[],
    ) {
    }
}
