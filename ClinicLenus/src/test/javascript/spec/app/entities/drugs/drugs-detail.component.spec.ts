/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { ClinicLenusTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { DrugsDetailComponent } from '../../../../../../main/webapp/app/entities/drugs/drugs-detail.component';
import { DrugsService } from '../../../../../../main/webapp/app/entities/drugs/drugs.service';
import { Drugs } from '../../../../../../main/webapp/app/entities/drugs/drugs.model';

describe('Component Tests', () => {

    describe('Drugs Management Detail Component', () => {
        let comp: DrugsDetailComponent;
        let fixture: ComponentFixture<DrugsDetailComponent>;
        let service: DrugsService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ClinicLenusTestModule],
                declarations: [DrugsDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    DrugsService,
                    JhiEventManager
                ]
            }).overrideTemplate(DrugsDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DrugsDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DrugsService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Drugs(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.drugs).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
