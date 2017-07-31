import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { ClinicPatientPrivateData } from './clinic-patient-private-data.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class ClinicPatientPrivateDataService {

    private resourceUrl = 'api/clinic-patient-private-data';

    constructor(private http: Http) { }

    create(clinicPatientPrivateData: ClinicPatientPrivateData): Observable<ClinicPatientPrivateData> {
        const copy = this.convert(clinicPatientPrivateData);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(clinicPatientPrivateData: ClinicPatientPrivateData): Observable<ClinicPatientPrivateData> {
        const copy = this.convert(clinicPatientPrivateData);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<ClinicPatientPrivateData> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            return res.json();
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convert(clinicPatientPrivateData: ClinicPatientPrivateData): ClinicPatientPrivateData {
        const copy: ClinicPatientPrivateData = Object.assign({}, clinicPatientPrivateData);
        return copy;
    }
}
