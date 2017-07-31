import { BaseEntity } from './../../shared';

export class Doctor implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public department?: string,
    ) {
    }
}
