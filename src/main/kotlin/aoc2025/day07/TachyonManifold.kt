package aoc2025.day07

data class TachyonManifold(
    val grid: List<Array<ManifoldCell>>
) {
    fun shotBeam() {
        for (lineIndex in 1..<grid.size)
            for (cellIndex in 0..<grid[lineIndex].size)
                propagateBeamParticles(lineIndex, cellIndex)
    }

    private fun propagateBeamParticles(lineIndex: Int, cellIndex: Int) {
        val targetCell = grid[lineIndex][cellIndex]
        val cellAbove = grid[lineIndex - 1][cellIndex]

        // Cleanup cell above target -> particles are moving downwards
        if (cellAbove is ManifoldCell.TachyonBeam) grid[lineIndex - 1][cellIndex] = ManifoldCell.Empty

        when {
            // Cell above was source for beam -> propagate beam
            targetCell is ManifoldCell.Empty && cellAbove is ManifoldCell.BeamSource ->
                grid.setOrAddBeamParticles(lineIndex, cellIndex, ManifoldCell.TachyonBeam())

            // Cell above was beam -> propagate beam
            targetCell is ManifoldCell.Empty && cellAbove is ManifoldCell.TachyonBeam ->
                grid.setOrAddBeamParticles(lineIndex, cellIndex, cellAbove)

            // Both target and above cells are beams -> merge
            targetCell is ManifoldCell.TachyonBeam && cellAbove is ManifoldCell.TachyonBeam ->
                grid.setOrAddBeamParticles(lineIndex, cellIndex, cellAbove)

            // Splitter and cell above was beam -> split beam
            targetCell is ManifoldCell.BeamSplitter.Deactivated && cellAbove is ManifoldCell.TachyonBeam -> {
                grid[lineIndex][cellIndex] = ManifoldCell.BeamSplitter.Activated
                grid.setOrAddBeamParticles(lineIndex, cellIndex - 1, cellAbove)
                grid.setOrAddBeamParticles(lineIndex, cellIndex + 1, cellAbove)
            }
        }
    }

    fun activatedBeamSplitCount(): Int =
        grid.sumOf { line -> line.filter { it is ManifoldCell.BeamSplitter.Activated }.size }

    fun allParticlesTimelinesCount(): Long =
        grid
            .last()
            .filter { it is ManifoldCell.TachyonBeam }
            .sumOf { (it as ManifoldCell.TachyonBeam).particlesNum }
}

fun List<Array<ManifoldCell>>.setOrAddBeamParticles(lineIndex: Int, cellIndex: Int, beam: ManifoldCell.TachyonBeam) =
    when (val targetCell = this[lineIndex][cellIndex]) {
        is ManifoldCell.TachyonBeam ->
            this[lineIndex][cellIndex] = ManifoldCell.TachyonBeam(targetCell.particlesNum + beam.particlesNum)

        is ManifoldCell.Empty ->
            this[lineIndex][cellIndex] = beam

        else ->
            throw IllegalArgumentException("Illegal attempt of deflect tachyon beam into invalid cell")
    }
