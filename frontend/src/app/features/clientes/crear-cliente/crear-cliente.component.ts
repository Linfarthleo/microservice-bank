import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ClienteService } from '../../../services/cliente.service';
import { CommonModule } from '@angular/common';
import { usernameValidator } from '../../../validators/username-validator';


@Component({
  selector: 'app-crear-cliente',
  standalone:true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './crear-cliente.component.html',
  styleUrl: './crear-cliente.component.scss'
})
export class CrearClienteComponent {
  clienteForm: FormGroup;
  mensajeError = '';

  constructor(private fb: FormBuilder, private clienteService: ClienteService) { 
    this.clienteForm = this.fb.group({
      nombre: ['', [Validators.required, Validators.minLength(3)]],
      genero: ['', Validators.required],
      edad: [null, [Validators.required, Validators.min(18), Validators.max(100)]],
      identificacion: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
      direccion: ['', Validators.required],
      telefono: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
      estado: [true, Validators.required],
      username: ['',
        [Validators.required, Validators.minLength(5), Validators.pattern(/^[a-zA-Z0-9]+$/)],
        [usernameValidator(this.clienteService)]
      ],
      password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(12)]]
    });
  }

  crearCliente(): void{
    if (this.clienteForm.valid) {
      this.clienteService.crearCliente(this.clienteForm.value).subscribe({
        next: () => {
          alert('Cliente creado con exito');
          this.clienteForm.reset();
        },
        error: (err) => {
          this.mensajeError = 'Error al registrar el cliente';
        }
      });
    } else {
      this.mensajeError = 'Revise los datos del formulario';
      }
    }
  }
