import { RouterModule, Routes } from '@angular/router';
import { ListarClientesComponent } from './features/clientes/listar-clientes/listar-clientes.component';
import { CrearClienteComponent } from './features/clientes/crear-cliente/crear-cliente.component';
import { EditarClienteComponent } from './features/clientes/editar-cliente/editar-cliente.component';
import { NgModule } from '@angular/core';

export const routes: Routes = [
    { path: 'clientes', component: ListarClientesComponent },
    { path: 'clientes/nuevo', component: CrearClienteComponent },
    { path: 'clientes/editar/:id', component: EditarClienteComponent },
    { path: '', redirectTo:'clientes', pathMatch:'full' },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule { }
