import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { ClinicOwnerInfo } from './clinic-owner-info.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class ClinicOwnerInfoService {

    private resourceUrl = 'api/clinic-owner-infos';

    constructor(private http: Http) { }

    create(clinicOwnerInfo: ClinicOwnerInfo): Observable<ClinicOwnerInfo> {
        const copy = this.convert(clinicOwnerInfo);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(clinicOwnerInfo: ClinicOwnerInfo): Observable<ClinicOwnerInfo> {
        const copy = this.convert(clinicOwnerInfo);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<ClinicOwnerInfo> {
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

    private convert(clinicOwnerInfo: ClinicOwnerInfo): ClinicOwnerInfo {
        const copy: ClinicOwnerInfo = Object.assign({}, clinicOwnerInfo);
        return copy;
    }
}
