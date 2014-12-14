package com.hawa.scrap.test;

import com.hawa.scrap.event.VolumePressEvent;
import com.hawa.scrap.ui.ImagePagerPresenterImpl;
import com.hawa.scrap.ui.ImagePagerView;
import junit.framework.TestCase;

import static org.mockito.Mockito.*;

public class ImagePagerPresenterImplTest extends TestCase {
    public void testHandleVolumePressShouldSetToNextIndexWhenVolumeDownPressed () {

        // Arrange
        ImagePagerView viewMock = mock(ImagePagerView.class);
        when(viewMock.getIndex()).thenReturn(0);
        ImagePagerPresenterImpl imagePagerPresenter = new ImagePagerPresenterImpl(viewMock, null, null);

        // Act
        imagePagerPresenter.handleVolumePress(new VolumePressEvent(VolumePressEvent.Volume.Down));

        // Assert
        verify(viewMock).setIndex(1);

    }

    public void testHandleVolumePressShouldSetToPreviousIndexWhenVolumeUpPressed () {

        // Arrange
        ImagePagerView viewMock = mock(ImagePagerView.class);
        when(viewMock.getIndex()).thenReturn(1);
        ImagePagerPresenterImpl imagePagerPresenter = new ImagePagerPresenterImpl(viewMock, null, null);

        // Act
        imagePagerPresenter.handleVolumePress(new VolumePressEvent(VolumePressEvent.Volume.Up));

        // Assert
        verify(viewMock).setIndex(0);

    }

    public void testHandleVolumePressShouldNotSetToPreviousIndexWhenVolumeUpPressedAtIndexZero () {

        // Arrange
        ImagePagerView viewMock = mock(ImagePagerView.class);
        when(viewMock.getIndex()).thenReturn(0);
        ImagePagerPresenterImpl imagePagerPresenter = new ImagePagerPresenterImpl(viewMock, null, null);

        // Act
        imagePagerPresenter.handleVolumePress(new VolumePressEvent(VolumePressEvent.Volume.Up));

        // Assert
        verify(viewMock, times(0)).setIndex(anyInt());

    }
}
