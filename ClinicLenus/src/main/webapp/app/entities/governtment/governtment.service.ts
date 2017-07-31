import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { Governtment } from './governtment.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class GoverntmentService {

    private resourceUrl = 'api/governtments';

    constructor(private http: Http) { }

    create(governtment: Governtment): Observable<Governtment> {
        const copy = this.convert(governtment);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(governtment: Governtment): Observable<Governtment> {
        const copy = this.convert(governtment);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<Governtment> {
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

    private convert(governtment: Governtment): Governtment {
        const copy: Governtment = Object.assign({}, governtment);
        return copy;
    }
}
