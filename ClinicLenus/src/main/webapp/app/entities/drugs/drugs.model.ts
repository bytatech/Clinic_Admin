import { BaseEntity } from './../../shared';

export class Drugs implements BaseEntity {
    constructor(
        public id?: number,
        public drugName?: string,
        public drugInstruction?: string,
        public frequency?: number,
        public totalPacking?: number,
        public drugs?: BaseEntity[],
    ) {
    }
}
