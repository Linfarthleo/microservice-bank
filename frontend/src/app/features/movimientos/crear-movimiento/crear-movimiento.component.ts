import { Component, OnInit } from '@angular/core'; 

import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms'; 

import { Router } from '@angular/router'; 

import { CommonModule } from '@angular/common'; 
import { Cuenta, Movimiento } from '../../../models/movimiento.model';
import { MovimientoService } from '../../../services/movimiento.service';
import { CuentaService } from '../../../services/cuenta.service';

 

@Component({ 

  selector: 'app-crear-movimiento', 

  standalone: true, 

  imports: [CommonModule, ReactiveFormsModule], 

  templateUrl: './crear-movimiento.component.html', 

  styleUrls: ['./crear-movimiento.component.scss'] 

}) 

export class CrearMovimientoComponent implements OnInit { 

  form: FormGroup; 

  cuentas: Cuenta[] = []; 

 

  constructor( 

    private fb: FormBuilder, 

    private movimientoService: MovimientoService, 

    private cuentaService: CuentaService, 

    private router: Router 

  ) { 

    this.form = this.fb.group({ 

      cuentaId: ['', Validators.required], 

      tipoMovimiento: ['', Validators.required], 

      valor: [null, [Validators.required, Validators.min(0.01)]], 

    }); 

  } 

 

  ngOnInit(): void { 

    this.cuentaService.obtenerCuentas().subscribe(data => { 

      this.cuentas = data; 

    }); 

  } 

 

  guardar(): void { 

    if (this.form.invalid) { 

      this.form.markAllAsTouched(); 

      return; 

    } 

 

    const movimiento: Movimiento = this.form.value; 

 

    this.movimientoService.crearMovimiento(movimiento).subscribe(() => { 

      this.router.navigate(['/movimientos']); 

    }); 

  } 

} 