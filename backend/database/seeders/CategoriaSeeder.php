<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use App\Models\Categoria;

class CategoriaSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        Categoria::create(['nombre' => 'Conciertos',   'encargado' => 'Juan Perez']);
        Categoria::create(['nombre' => 'Conferencias', 'encargado' => 'Ana Garcia']);
        Categoria::create(['nombre' => 'Talleres',     'encargado' => 'Carlos Ruiz']);
    }
}
