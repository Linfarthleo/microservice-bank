import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarCuentasComponent } from './listar-cuentas.component';

describe('ListarCuentasComponent', () => {
  let component: ListarCuentasComponent;
  let fixture: ComponentFixture<ListarCuentasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListarCuentasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListarCuentasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
