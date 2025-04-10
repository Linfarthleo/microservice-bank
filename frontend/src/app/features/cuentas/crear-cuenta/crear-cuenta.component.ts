import { Component, OnInit } from '@angular/core'; 

import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms'; 

import { Router } from '@angular/router'; 

import { CommonModule } from '@angular/common'; 
import { Cliente } from '../../../models/cliente.model';
import { CuentaService } from '../../../services/cuenta.service';
import { ClienteService } from '../../../services/cliente.service';


@Component({ 

  selector: 'app-crear-cuenta', 

  standalone: true, 

  imports: [CommonModule, ReactiveFormsModule], 

  templateUrl: './crear-cuenta.component.html', 

  styleUrls: ['./crear-cuenta.component.scss'] 

}) 

export class CrearCuentaComponent implements OnInit { 

  form: FormGroup; 

  clientes: Cliente[] = []; 

 

  constructor( 

    private fb: FormBuilder, 

    private cuentaService: CuentaService, 

    private clienteService: ClienteService, 

    private router: Router 

  ) { 

    this.form = this.fb.group({ 

      numeroCuenta: ['', [Validators.required, Validators.minLength(10)]], 

      tipoCuenta: ['', Validators.required], 

      saldoInicial: [0, [Validators.required, Validators.min(0)]], 

      estado: [true, Validators.required], 

      clienteId: ['', Validators.required], 

    }); 

  } 

 

  ngOnInit(): void { 

    this.clienteService.obtenerClientes().subscribe(clientes => { 

      this.clientes = clientes; 

    }); 

  } 

 

  guardar(): void { 

    if (this.form.invalid) { 

      this.form.markAllAsTouched(); 

      return; 

    } 


    this.cuentaService.crearCuenta(this.form.value).subscribe(() => { 

      this.router.navigate(['/cuentas']); 

    }); 

  } 

} 