import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { Drugs } from './drugs.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class DrugsService {

    private resourceUrl = 'api/drugs';

    constructor(private http: Http) { }

    create(drugs: Drugs): Observable<Drugs> {
        const copy = this.convert(drugs);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(drugs: Drugs): Observable<Drugs> {
        const copy = this.convert(drugs);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<Drugs> {
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

    private convert(drugs: Drugs): Drugs {
        const copy: Drugs = Object.assign({}, drugs);
        return copy;
    }
}
