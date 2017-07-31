import { BaseEntity } from './../../shared';

export class ClinicOwnerInfo implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public designation?: string,
        public mobile?: number,
        public email?: string,
        public address?: string,
        public registrationNo?: number,
    ) {
    }
}
