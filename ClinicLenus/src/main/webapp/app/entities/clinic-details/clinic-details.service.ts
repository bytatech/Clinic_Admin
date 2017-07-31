import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils } from 'ng-jhipster';

import { ClinicDetails } from './clinic-details.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class ClinicDetailsService {

    private resourceUrl = 'api/clinic-details';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(clinicDetails: ClinicDetails): Observable<ClinicDetails> {
        const copy = this.convert(clinicDetails);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(clinicDetails: ClinicDetails): Observable<ClinicDetails> {
        const copy = this.convert(clinicDetails);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<ClinicDetails> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
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
        for (let i = 0; i < jsonResponse.length; i++) {
            this.convertItemFromServer(jsonResponse[i]);
        }
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convertItemFromServer(entity: any) {
        entity.clinicTiming = this.dateUtils
            .convertLocalDateFromServer(entity.clinicTiming);
    }

    private convert(clinicDetails: ClinicDetails): ClinicDetails {
        const copy: ClinicDetails = Object.assign({}, clinicDetails);
        copy.clinicTiming = this.dateUtils
            .convertLocalDateToServer(clinicDetails.clinicTiming);
        return copy;
    }
}
