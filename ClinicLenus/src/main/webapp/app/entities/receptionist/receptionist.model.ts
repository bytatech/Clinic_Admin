import { BaseEntity } from './../../shared';

export class Receptionist implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
    ) {
    }
}
