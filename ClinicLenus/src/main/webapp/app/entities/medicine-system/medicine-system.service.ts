import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { MedicineSystem } from './medicine-system.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class MedicineSystemService {

    private resourceUrl = 'api/medicine-systems';

    constructor(private http: Http) { }

    create(medicineSystem: MedicineSystem): Observable<MedicineSystem> {
        const copy = this.convert(medicineSystem);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(medicineSystem: MedicineSystem): Observable<MedicineSystem> {
        const copy = this.convert(medicineSystem);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<MedicineSystem> {
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

    private convert(medicineSystem: MedicineSystem): MedicineSystem {
        const copy: MedicineSystem = Object.assign({}, medicineSystem);
        return copy;
    }
}
