import { BaseEntity } from './../../shared';

export class ClinicDetails implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public address?: string,
        public pincode?: number,
        public country?: string,
        public mobile?: number,
        public clinicTiming?: any,
        public category?: string,
        public landmarks?: string,
        public website?: string,
        public clinicDescription?: string,
        public clinicImagesContentType?: string,
        public clinicImages?: any,
        public location?: string,
        public clinicOwnerInfo?: BaseEntity,
        public governtment?: BaseEntity,
        public nonGoverntment?: BaseEntity,
        public medicineSystem?: BaseEntity,
    ) {
    }
}
